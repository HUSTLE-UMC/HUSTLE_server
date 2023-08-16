package com.sporthustle.hustle.competitions.competition.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CompetitionContactRequestDTO {

  @NotBlank(message = "이름은 필수 입력 값입니다.")
  private String name;

  @NotBlank(message = "연락처는 필수 입력 값입니다.")
  private String phoneNumber;
}
