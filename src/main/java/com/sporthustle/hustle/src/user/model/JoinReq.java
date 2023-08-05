package com.sporthustle.hustle.src.user.model;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class JoinReq {

  @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
  @NotBlank(message = "이메일은 필수 입력 값입니다.")
  private String email;

  @Pattern(
      regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$",
      message = "비밀번호 형식이 올바르지 않습니다.")
  private String password;

  @NotBlank(message = "이름은 필수 입력 값입니다.")
  private String name;

  @NotBlank(message = "생일은 필수 입력 값입니다.")
  private Date birth;

  @NotBlank(message = "성별은 필수 입력 값입니다.")
  private String gender;

  @NotBlank(message = "역할은 필수 입력 값입니다.")
  private String roles;

  @NotBlank(message = "대학 아이디값은 필수 입력 값입니다.")
  private Long universityId;
}
