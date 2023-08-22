package com.sporthustle.hustle.competitions.match.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateMatchResultPostRequestDTO {

  @NotNull(message = "경기 수는 필수 입력 값입니다.")
  private Integer postOrder;

  @NotNull(message = "경기 시각은 필수 입력 값입니다.")
  private LocalDateTime matchTime;

  @NotNull(message = "첫 번째 참가팀은 필수 입력 값입니다.")
  private Long homeEntryTeamId;

  @NotNull(message = "두 번째 참가팀은 필수 입력 값입니다.")
  private Long awayEntryTeamId;
}
