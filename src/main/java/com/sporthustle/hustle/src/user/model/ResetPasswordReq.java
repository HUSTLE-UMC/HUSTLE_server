package com.sporthustle.hustle.src.user.model;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ResetPasswordReq {

  @NotBlank(message = "아이디값은 필수 입력 값입니다.")
  private String email;

  @NotBlank(message = "이름은 필수 입력 값입니다.")
  private String name;

  @NotBlank(message = "생년월일은 필수 입력 값입니다.")
  private Date birth;

  @NotBlank(message = "새 비밀번호는 필수 입력 값입니다.")
  private String newPassword;
}
