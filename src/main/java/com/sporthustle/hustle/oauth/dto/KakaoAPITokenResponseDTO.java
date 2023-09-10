package com.sporthustle.hustle.oauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class KakaoAPITokenResponseDTO {
  @JsonProperty(value = "access_token")
  public String accessToken;

  @JsonProperty(value = "refresh_token")
  public String refreshToken;

  @JsonProperty(value = "token_type")
  public String tokenType;

  @JsonProperty(value = "id_token")
  public String idToken;

  @JsonProperty(value = "expires_in")
  public Long expiresIn;

  @JsonProperty(value = "refresh_token_expires_in")
  public Long refreshTokenExpiresIn;

  public String scope;
}
