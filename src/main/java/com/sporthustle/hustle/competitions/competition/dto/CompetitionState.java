package com.sporthustle.hustle.competitions.competition.dto;

import java.time.LocalDateTime;

public enum CompetitionState {
  BEFORE, // 대회 모집 예정, 모집 시작 전
  RECRUITING, // 대회 모집 중
  ACTIVE, // 대회 진행 중
  COMPLETE, // 대회 완료
  ;

  public static CompetitionState from(
      LocalDateTime recruitmentStartDate,
      LocalDateTime recruitmentEndDate,
      LocalDateTime startDate,
      LocalDateTime endDate) {
    if (CompetitionState.isBefore(recruitmentStartDate)) {
      return CompetitionState.BEFORE;
    } else if (CompetitionState.isRecruiting(recruitmentStartDate, recruitmentEndDate)) {
      return CompetitionState.RECRUITING;
    } else if (CompetitionState.isActive(startDate, endDate)) {
      return CompetitionState.ACTIVE;
    } else if (CompetitionState.isComplete(endDate)) {
      return CompetitionState.COMPLETE;
    }

    throw new IllegalArgumentException("의도치 않은 CompetitionState 을 반환합니다.");
  }

  private static boolean isBefore(LocalDateTime recruitmentStartDate) {
    LocalDateTime now = LocalDateTime.now();
    return recruitmentStartDate.isAfter(now);
  }

  private static boolean isRecruiting(
      LocalDateTime recruitmentStartDate, LocalDateTime recruitmentEndDate) {
    LocalDateTime now = LocalDateTime.now();
    return (recruitmentStartDate.isEqual(now) || recruitmentStartDate.isBefore(now))
        && (recruitmentEndDate.isEqual(now) || recruitmentEndDate.isAfter(now));
  }

  private static boolean isActive(LocalDateTime startDate, LocalDateTime endDate) {
    LocalDateTime now = LocalDateTime.now();
    return (startDate.isEqual(now) || startDate.isBefore(now))
        && (endDate.isEqual(now) || endDate.isAfter(now));
  }

  private static boolean isComplete(LocalDateTime endDate) {
    LocalDateTime now = LocalDateTime.now();
    return endDate.isBefore(now);
  }
}
