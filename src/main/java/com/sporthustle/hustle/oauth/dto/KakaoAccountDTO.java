package com.sporthustle.hustle.oauth.dto;

import lombok.Getter;

@Getter
public class KakaoAccountDTO {
  private KakaoAccountProfileDTO profile;
  private String email;
  private String birthday;
  private String gender;
}
