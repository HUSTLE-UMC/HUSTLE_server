package com.sporthustle.hustle.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdatePasswordRequestDTO {

  @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
  @NotBlank(message = "이메일은 필수 입력 값입니다.")
  private String email;

  @NotBlank(message = "새 비밀번호는 필수 입력 값입니다.")
  private String newPassword;
}
