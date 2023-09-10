package com.sporthustle.hustle.oauth;

import static com.sporthustle.hustle.common.consts.Constants.*;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.common.jwt.dto.TokenInfo;
import com.sporthustle.hustle.oauth.dto.GetKakaoTokenResponseDTO;
import com.sporthustle.hustle.oauth.dto.GetKakaoUserInformationResponseDTO;
import com.sporthustle.hustle.oauth.dto.KakaoAPIGetUserResponseDTO;
import com.sporthustle.hustle.oauth.dto.KakaoAPITokenResponseDTO;
import com.sporthustle.hustle.oauth.exception.BadGatewayException;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthService {

  private final KakaoInformationParser kakaoInformationParser;
  private final OAuthConnector connector;

  @Value("${oauth.kakao.secret.client-id}")
  private String clientId;

  @Value("${oauth.kakao.secret.redirect-url}")
  private String redirectUrl;

  private final WebClient webClient;

  public GetKakaoTokenResponseDTO getKakaoToken(String code) {
    KakaoAPITokenResponseDTO response =
        webClient
            .post()
            .uri(KAKAO_REQUEST_URL_FOR_TOKEN)
            .header("Content-Type", "application/x-www-form-urlencoded")
            .body(
                BodyInserters.fromFormData("grant_type", "authorization_code")
                    .with("client_id", clientId)
                    .with("redirect_uri", redirectUrl)
                    .with("code", code))
            .retrieve()
            .onStatus(
                httpStatus -> httpStatus.is4xxClientError(),
                clientResponse ->
                    clientResponse
                        .bodyToMono(String.class)
                        .map(message -> BaseException.from(ErrorCode.INVALID_AUTH_CODE)))
            .onStatus(
                httpStatus -> httpStatus.is5xxServerError(),
                clientResponse ->
                    clientResponse
                        .bodyToMono(String.class)
                        .map(message -> new BadGatewayException()))
            .bodyToMono(KakaoAPITokenResponseDTO.class)
            .timeout(Duration.ofSeconds(3L))
            .retryWhen(
                Retry.backoff(3, Duration.ofSeconds(3L))
                    .filter(throwable -> throwable instanceof BadGatewayException))
            .block();

    return GetKakaoTokenResponseDTO.builder()
        .code("SUCCESS_GET_KAKAO_TOKEN")
        .message("성공적으로 토큰을 조회했습니다.")
        .data(
            TokenInfo.builder()
                .accessToken(response.accessToken)
                .refreshToken(response.refreshToken)
                .build())
        .build();
  }

  public GetKakaoUserInformationResponseDTO getKakaoUserInformation(String kakaoAccessToken)
      throws BaseException {
    KakaoAPIGetUserResponseDTO response =
        webClient
            .post()
            .uri(KAKAO_REQUEST_URL_FOR_INFORMATION)
            .header("Content-Type", "application/x-www-form-urlencoded")
            .header("Authorization", "Bearer " + kakaoAccessToken)
            .retrieve()
            .onStatus(
                httpStatus -> httpStatus.is4xxClientError(),
                clientResponse ->
                    clientResponse
                        .bodyToMono(String.class)
                        .map(message -> BaseException.from(ErrorCode.INVALID_AUTH_CODE)))
            .onStatus(
                httpStatus -> httpStatus.is5xxServerError(),
                clientResponse ->
                    clientResponse
                        .bodyToMono(String.class)
                        .map(message -> new BadGatewayException()))
            .bodyToMono(KakaoAPIGetUserResponseDTO.class)
            .timeout(Duration.ofSeconds(3L))
            .retryWhen(
                Retry.backoff(3, Duration.ofSeconds(3L))
                    .filter(throwable -> throwable instanceof BadGatewayException))
            .block();

    return GetKakaoUserInformationResponseDTO.builder()
        .oauthId(response.snsId)
        .email(response.getKakaoAccount().getEmail())
        .name(response.getKakaoAccount().getProfile().getNickname())
        .password(getRandomPassword())
        .gender(response.getKakaoAccount().getGender())
        .build();
  }

  private String getRandomPassword() {
    return RandomStringUtils.randomAlphanumeric(100);
  }
}
