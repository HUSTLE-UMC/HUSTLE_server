package com.sporthustle.hustle.auth.dto.oauth;

import com.sporthustle.hustle.common.dto.BaseResponse;
import com.sporthustle.hustle.common.jwt.dto.TokenInfo;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class OAuthSignInResponseDTO extends BaseResponse<TokenInfo> {}
