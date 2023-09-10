package com.sporthustle.hustle.auth.dto.oauth;

import com.sporthustle.hustle.common.dto.BaseResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class OAuthSignUpResponseDTO extends BaseResponse<Boolean> {}
