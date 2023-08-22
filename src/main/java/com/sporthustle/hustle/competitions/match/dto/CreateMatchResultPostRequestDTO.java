package com.sporthustle.hustle.competitions.match.dto;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateMatchResultPostRequestDTO {

  @NotBlank(message = "경기 제목은 필수 입력 값입니다.")
  private String title;

  @NotBlank(message = "경기 그룹 카테고리는 필수 입력 값입니다.")
  private String groupCategory;

  @NotNull(message = "경기 수는 필수 입력 값입니다.")
  private Integer postOrder;

  @NotNull(message = "경기 시각은 필수 입력 값입니다.")
  private LocalDateTime matchTime;

  private String mediaUrl;

  @NotNull(message = "첫 번째 참가팀은 필수 입력 값입니다.")
  private Long homeEntryTeamId;

  @NotNull(message = "첫 번째 참가팀 점수는 필수 입력 값입니다.")
  private Integer homeScore;

  @NotNull(message = "첫 번째 참가팀 승리 여부는 필수 입력 값입니다.")
  private Boolean isHomeWin;

  @NotNull(message = "두 번째 참가팀은 필수 입력 값입니다.")
  private Long awayEntryTeamId;

  @NotNull(message = "두 번째 참가팀 점수는 필수 입력 값입니다.")
  private Integer awayScore;

  @NotNull(message = "두 번째 참가팀 승리 여부는 필수 입력 값입니다.")
  private Boolean isAwayWin;

  @NotNull(message = "첫 번째 팀 득점 기록 목록은 필수 입력 값입니다.")
  private List<CreateMatchResultPostScoreLogRequestDTO> homeMatchResultPostScoreLogs;

  @NotNull(message = "두 번째 팀 득점 기록 목록은 필수 입력 값입니다.")
  private List<CreateMatchResultPostScoreLogRequestDTO> awayMatchResultPostScoreLogs;
}
