package com.sporthustle.hustle.common.security.model;

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
