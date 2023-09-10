package com.sporthustle.hustle.oauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class KakaoAPIGetUserResponseDTO {
  @JsonProperty(value = "id")
  public String snsId;

  @JsonProperty(value = "kakao_account")
  public KakaoAccountDTO kakaoAccount;
}
