package com.sporthustle.hustle.src.user.model;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class FindAccountReq {

  private String name;

  private Date birth;

  @NotBlank(message = "이메일은 필수 입력입니다.")
  private String email;
}
