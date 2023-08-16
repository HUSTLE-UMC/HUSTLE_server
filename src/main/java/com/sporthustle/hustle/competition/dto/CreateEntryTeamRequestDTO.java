package com.sporthustle.hustle.competition.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CreateEntryTeamRequestDTO {

  @NotBlank(message = "이름은 필수 입력 값입니다.")
  private String name;

  @NotBlank(message = "휴대폰 번호는 필수 입력 값입니다.")
  private String phoneNumber;

  @NotNull(message = "동아리는 필수 입력 값입니다.")
  private Long clubId;

}
