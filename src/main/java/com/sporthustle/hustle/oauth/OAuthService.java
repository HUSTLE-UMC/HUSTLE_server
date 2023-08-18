package com.sporthustle.hustle.oauth;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sporthustle.hustle.common.exception.BaseException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.sporthustle.hustle.common.jwt.dto.TokenInfo;
import com.sporthustle.hustle.oauth.dto.OAuthUserInfoResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OAuthService {

  @Value("${oauth.secret.client-id}")
  private String clientId;

  @Value("${oauth.secret.redirect-url}")
  private String redirectUrl;

  public TokenInfo getKakaoToken(String code) {
    String access_Token = "";
    String refresh_Token = "";
    String reqURL = "https://kauth.kakao.com/oauth/token";

    try {
      URL url = new URL(reqURL);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();

      // POST 요청을 위해 기본값이 false인 setDoOutput을 true로
      conn.setRequestMethod("POST");
      conn.setDoOutput(true);

      // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
      StringBuilder sb = new StringBuilder();
      sb.append("grant_type=authorization_code");
      sb.append("&client_id=" + clientId); // TODO REST_API_KEY 입력
      sb.append("&redirect_uri=" + redirectUrl); // TODO 인가코드 받은 redirect_uri 입력
      sb.append("&code=" + code);
      bw.write(sb.toString());
      bw.flush();

      // 결과 코드가 200이라면 성공
      int responseCode = conn.getResponseCode();
      System.out.println("responseCode : " + responseCode);

      // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String line = "";
      String result = "";

      while ((line = br.readLine()) != null) {
        result += line;
      }
      System.out.println("response body : " + result);

      // Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
      JsonParser parser = new JsonParser();
      JsonElement element = parser.parse(result);

      access_Token = element.getAsJsonObject().get("access_token").getAsString();
      refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

      br.close();
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return TokenInfo.builder().accessToken(access_Token).refreshToken(refresh_Token).build();
  }

  public OAuthUserInfoResponseDTO getKakaoUserInfo(String token) throws BaseException {

    String reqURL = "https://kapi.kakao.com/v2/user/me";

    // access_token을 이용하여 사용자 정보 조회
    try {
      URL url = new URL(reqURL);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();

      conn.setRequestMethod("POST");
      conn.setDoOutput(true);
      conn.setRequestProperty("Authorization", "Bearer " + token); // 전송할 header 작성, access_token전송

      // 결과 코드가 200이라면 성공
      int responseCode = conn.getResponseCode();
      log.info("responseCode : " + responseCode);

      // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String line = "";
      String result = "";

      while ((line = br.readLine()) != null) {
        result += line;
      }
      System.out.println("response body : " + result);

      // Gson 라이브러리로 JSON파싱
      JsonParser parser = new JsonParser();
      JsonElement element = parser.parse(result);

      long id = element.getAsJsonObject().get("id").getAsInt();
      boolean hasEmail =
          element
              .getAsJsonObject()
              .get("kakao_account")
              .getAsJsonObject()
              .get("has_email")
              .getAsBoolean();
      String email = "";
      if (hasEmail) {
        email =
            element
                .getAsJsonObject()
                .get("kakao_account")
                .getAsJsonObject()
                .get("email")
                .getAsString();
      }
      String nickname = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("profile").getAsJsonObject().get("nickname").getAsString();
      boolean has_birthday = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_birthday").getAsBoolean();
      String birthday = "";
      if (has_birthday) {
        birthday = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("birthday").getAsString();
      }
      boolean has_gender = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_gender").getAsBoolean();
      String gender = "";
      if (has_gender) {
        gender = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("gender").getAsString();
      }

      System.out.println("id : " + id);
      System.out.println("email : " + email);
      System.out.println("nickname : " + nickname);
      System.out.println("birthday : " + birthday);
      System.out.println("gender : " + gender);

      br.close();
      return OAuthUserInfoResponseDTO.builder()
              .email(email)
              .name(nickname)
              .password(getRamdomPassword())
              .gender(gender).build();

    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private String getRamdomPassword() {
    return RandomStringUtils.randomAlphanumeric(100);
  }
}
