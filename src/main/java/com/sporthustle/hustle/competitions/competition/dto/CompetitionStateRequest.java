package com.sporthustle.hustle.competitions.competition.dto;

public enum CompetitionStateRequest {
  ACTIVE,
  COMPLETE,
  ;

  public static CompetitionStateRequest of(String competitionStateRequestString) {
    if (competitionStateRequestString == null) {
      throw new IllegalArgumentException("competitionStateRequestString 이 null 입니다.");
    }

    for (CompetitionStateRequest competitionStateRequest : CompetitionStateRequest.values()) {
      if (competitionStateRequest.name().equals(competitionStateRequestString)) {
        return competitionStateRequest;
      }
    }

    return CompetitionStateRequest.ACTIVE;
  }
}
