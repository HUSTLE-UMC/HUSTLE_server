package com.sporthustle.hustle.auth.dto.oauth;

import com.sporthustle.hustle.common.jwt.dto.TokenInfo;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OAuthSignInResponseDTO {

  private TokenInfo tokenInfo;
}
