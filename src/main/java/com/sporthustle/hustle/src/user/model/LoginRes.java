package com.sporthustle.hustle.src.user.model;

import com.sporthustle.hustle.common.jwt.model.TokenInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRes {

  private TokenInfo tokenInfo;
}
