package com.sporthustle.hustle.oauth.dto;

import com.sporthustle.hustle.common.dto.BaseResponse;
import com.sporthustle.hustle.common.jwt.dto.TokenInfo;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class GetKakaoTokenResponseDTO extends BaseResponse<TokenInfo> {}
