package com.sporthustle.hustle.user.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class FindEmailRequestDTO {

  @NotBlank(message = "이름은 필수 입력 값입니다.")
  private String name;

  @NotBlank(message = "생일은 필수 입력 값입니다.")
  private LocalDate birthday;
}
