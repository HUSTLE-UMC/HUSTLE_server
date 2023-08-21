package com.sporthustle.hustle.oauth;

import static com.sporthustle.hustle.common.consts.Constants.*;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.jwt.dto.KakaoTokenInfo;
import com.sporthustle.hustle.oauth.dto.OAuthUserInfoResponseDTO;
import com.sporthustle.hustle.oauth.utils.OAuthConnector;
import com.sporthustle.hustle.oauth.utils.kakao.KakaoInformationParser;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthService {

  private final KakaoInformationParser kakaoInformationParser;
  private final OAuthConnector connector;

  @Value("${oauth.secret.client-id}")
  private String clientId;

  @Value("${oauth.secret.redirect-url}")
  private String redirectUrl;

  public KakaoTokenInfo getKakaoToken(String code) {
    String access_Token = "";
    String refresh_Token = "";
    String reqURL = KAKAO_REQUEST_URL_FOR_TOKEN;

    try {
      URL url = new URL(reqURL);
      HttpURLConnection conn = connector.getConnectionForKakaoToken(url);

      // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
      connector.transportKakaoUrl(conn, clientId, redirectUrl, code);

      // 결과 코드가 200이라면 성공
      printLogForConnection(conn);

      // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
      String jsonResultForToken = kakaoInformationParser.getJsonForKakaoToken(conn);

      // Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
      JsonParser parser = new JsonParser();
      JsonElement element = parser.parse(jsonResultForToken);

      access_Token = kakaoInformationParser.getAccessToken(element);
      refresh_Token = kakaoInformationParser.getRefreshToken(element);

    } catch (IOException e) {
      e.printStackTrace();
    }

    return KakaoTokenInfo.builder().accessToken(access_Token).refreshToken(refresh_Token).build();
  }

  public OAuthUserInfoResponseDTO getKakaoUserInfo(String token) throws BaseException {

    String reqURL = KAKAO_REQUEST_URL_FOR_INFORMATION;

    // access_token을 이용하여 사용자 정보 조회
    try {
      URL url = new URL(reqURL);
      HttpURLConnection conn = connector.getConnectionForUserInformation(url, token);

      // 결과 코드가 200이라면 성공
      printLogForConnection(conn);

      // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
      String jsonResult = kakaoInformationParser.getJsonForKakaoUserInformation(conn);

      // Gson 라이브러리로 JSON파싱
      JsonParser parser = new JsonParser();
      JsonElement element = parser.parse(jsonResult);

      String snsId = kakaoInformationParser.getSnsIdByParsing(element);
      String email = kakaoInformationParser.getEmailByParsing(element);
      String name = kakaoInformationParser.getNameByParsing(element);
      String gender = kakaoInformationParser.getGenderByParsing(element);

      return OAuthUserInfoResponseDTO.builder()
          .oauthId(snsId)
          .email(email)
          .name(name)
          .password(getRamdomPassword())
          .gender(gender)
          .build();

    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private String getRamdomPassword() {
    return RandomStringUtils.randomAlphanumeric(100);
  }

  private void printLogForConnection(HttpURLConnection conn) throws IOException {
    int responseCode = conn.getResponseCode();
    log.info("responseCode : " + responseCode);
  }
}
