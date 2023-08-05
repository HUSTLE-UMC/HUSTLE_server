package com.sporthustle.hustle.src.user.model;

import com.sporthustle.hustle.src.user.entity.Gender;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ModifyUserInfoReq {

  @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
  private String password;

  @NotBlank(message = "이름은 필수 입력 값입니다.")
  private String name;

  @NotBlank(message = "생일은 필수 입력 값입니다.")
  private Date birth;

  @NotBlank(message = "성별은 필수 입력 값입니다.")
  private Gender gender;

  @NotBlank(message = "대학교 아이디값은 필수 입력 값입니다.")
  private Long universityId;
}
