package com.sporthustle.hustle.auth.dto.oauth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class OAuthSignInRequestDTO {

  @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
  @NotBlank(message = "이메일은 필수 입력 값입니다.")
  private String email;
}
