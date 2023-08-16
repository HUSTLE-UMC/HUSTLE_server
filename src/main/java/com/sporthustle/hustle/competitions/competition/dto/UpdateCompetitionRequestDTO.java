package com.sporthustle.hustle.competitions.competition.dto;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateCompetitionRequestDTO {

  @NotBlank(message = "대회 이름은 필수 입력 값입니다.")
  private String title;

  @NotBlank(message = "주최자는 필수 입력 값입니다.")
  private String host;

  @NotBlank(message = "대회 개최지 주소는 필수 입력 값입니다.")
  private String place;

  @NotNull(message = "대회 시작 일자는 필수 입력 값입니다.")
  private LocalDateTime startDate;

  @NotNull(message = "대회 종료 일자는 필수 입력 값입니다.")
  private LocalDateTime endDate;

  @NotNull(message = "대회 모집 시작 일자는 필수 입력 값입니다.")
  private LocalDateTime recruitmentStartDate;

  @NotNull(message = "대회 모집 종료 일자는 필수 입력 값입니다.")
  private LocalDateTime recruitmentEndDate;

  @NotNull(message = "참가비는 필수 입력 값입니다.")
  private Long entryFee;

  @NotNull(message = "최대 참가팀 수는 필수 입력 값입니다.")
  private Integer maxEntryCount;

  @NotBlank(message = "후원 단체 명은 필수 입력 값입니다.")
  private String sponsor;

  @NotBlank(message = "포스터 이미지 주소는 필수 입력 값입니다.")
  private String posterUrl;

  @NotNull(message = "예선 조 수는 필수 입력 값입니다.")
  private Integer preRoundGroupCount;

  @NotNull(message = "본선 진출 수는 필수 입력 값입니다.")
  private Integer finalRoundTeamCount;

  @NotNull(message = "연락처는 필수 입력 값입니다.")
  List<CompetitionContactRequestDTO> contacts;

  @NotNull(message = "종목은 필수 입력 값입니다.")
  private Long sportEventId;
}
