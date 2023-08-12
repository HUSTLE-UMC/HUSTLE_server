package com.sporthustle.hustle.auth.dto;

import com.sporthustle.hustle.common.jwt.dto.TokenInfo;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetAccessTokenResponseDTO {

  private TokenInfo tokenInfo;
}
