package com.sporthustle.hustle.common.jwt.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenInfo {
  private String accessToken;
  private String refreshToken;
}
