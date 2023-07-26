package com.sporthustle.hustle.src.user.model;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SearchUserIdReq {

  @NotBlank(message = "이름은 필수 입력 값입니다.")
  private String name;

  @NotBlank(message = "생일은 필수 입력 값입니다.")
  private Date birth;
}
