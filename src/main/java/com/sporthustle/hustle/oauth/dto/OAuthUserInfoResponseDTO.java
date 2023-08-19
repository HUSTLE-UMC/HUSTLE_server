package com.sporthustle.hustle.oauth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuthUserInfoResponseDTO {
  private String email;
  private String password;
  private String name;
  private String gender;
}
