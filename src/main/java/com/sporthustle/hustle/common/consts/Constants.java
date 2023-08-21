package com.sporthustle.hustle.common.consts;

import java.util.List;

public class Constants {
  // For JWT Authentication
  public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
  public static final String REFRESH_TOKEN = "REFRESH_TOKEN";
  public static final String TOKEN_TYPE = "type";
  public static final Long ACCESS_TOKEN_EXPIRATION_TIME = 1000L * 60 * 30;
  public static final Long REFRESH_TOKEN_EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 30;

  // For Spring Security
  public static final String FRONTEND_LOCAL_ORIGIN = "http://localhost:3000";
  public static final List<String> ALLOWED_DOMAIN_ORIGINS =
      List.of(
          FRONTEND_LOCAL_ORIGIN,
          "https://sport-hustle.com",
          "https://www.sport-hustle.com",
          "https://api.sport-hustle.com");
  public static final String PERMIT_ALL = "*";

  // For Oauth
  public static final String KAKAO_REQUEST_URL_FOR_TOKEN = "https://kauth.kakao.com/oauth/token";
  public static final String KAKAO_REQUEST_URL_FOR_INFORMATION =
      "https://kapi.kakao.com/v2/user/me";
}
