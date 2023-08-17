package com.sporthustle.hustle.competitions.ingame;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.competitions.ingame.entity.FinalRoundDetail;
import com.sporthustle.hustle.competitions.ingame.entity.PreRoundDetail;
import com.sporthustle.hustle.competitions.ingame.repository.FinalRoundDetailRepository;
import com.sporthustle.hustle.competitions.ingame.repository.PreRoundDetailRepository;

public class CompetitionDetailUtils {
  public static final String DEFAULT_TIMETABLE_URL = "";

  public static PreRoundDetail getPreRoundDetail(
      Long competitionId, PreRoundDetailRepository preRoundDetailRepository) {
    PreRoundDetail preRoundDetail =
        preRoundDetailRepository
            .findByCompetition_Id(competitionId)
            .orElseThrow(() -> BaseException.from(ErrorCode.PRE_ROUND_DETAIL_NOT_FOUND));
    return preRoundDetail;
  }

  public static FinalRoundDetail getFinalRoundDetail(
      Long competitionId, FinalRoundDetailRepository finalRoundDetailRepository) {
    FinalRoundDetail finalRoundDetail =
        finalRoundDetailRepository
            .findByCompetition_Id(competitionId)
            .orElseThrow(() -> BaseException.from(ErrorCode.PRE_ROUND_DETAIL_NOT_FOUND));
    return finalRoundDetail;
  }
}
