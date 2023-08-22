package com.sporthustle.hustle.competitions.competition;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.competitions.competition.entity.Competition;
import com.sporthustle.hustle.competitions.competition.repository.CompetitionRepository;

public class CompetitionUtils {

  public static Competition getCompetitionById(
      Long competitionId, CompetitionRepository competitionRepository) {
    Competition competition =
        competitionRepository
            .findById(competitionId)
            .orElseThrow(() -> BaseException.from(ErrorCode.COMPETITION_NOT_FOUND));
    return competition;
  }
}
