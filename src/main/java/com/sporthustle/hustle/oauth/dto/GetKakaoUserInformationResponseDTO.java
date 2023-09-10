package com.sporthustle.hustle.oauth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetKakaoUserInformationResponseDTO {
  private String oauthId;
  private String email;
  private String password;
  private String name;
  private String gender;
}
