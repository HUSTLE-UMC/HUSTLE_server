package com.sporthustle.hustle.competitions.ingame;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.competitions.ingame.entity.PreRoundGroup;
import com.sporthustle.hustle.competitions.ingame.repository.PreRoundGroupRepository;

public class InGameUtils {

  public static PreRoundGroup getPreRoundGroupByCompetitionAndEntryTeam(
      Long competitionId, Long entryTeamId, PreRoundGroupRepository preRoundGroupRepository) {
    PreRoundGroup preRoundGroup =
        preRoundGroupRepository
            .findByCompetition_IdAndEntryTeam_Id(competitionId, entryTeamId)
            .orElseThrow(() -> BaseException.from(ErrorCode.PREROUND_NOT_FOUND));
    return preRoundGroup;
  }
}
