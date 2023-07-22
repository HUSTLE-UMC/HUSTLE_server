package com.sporthustle.hustle.common.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TokenInfo {
  private String accessToken;
  private String refreshToken;
}
