package com.sporthustle.hustle.competitions.entryteam;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.competitions.entryteam.entity.EntryTeam;
import com.sporthustle.hustle.competitions.entryteam.repository.EntryTeamRepository;

public class EntryTeamUtils {
  public static EntryTeam getEntryTeamByIdAndCompetitionId(
      Long id, Long competitionId, EntryTeamRepository entryTeamRepository) {
    EntryTeam entryTeam =
        entryTeamRepository
            .findByIdAndCompetition_Id(id, competitionId)
            .orElseThrow(() -> BaseException.from(ErrorCode.ENTRY_TEAM_NOT_FOUND));

    return entryTeam;
  }

  public static EntryTeam getEntryTeamByUserIdAndCompetitionId(
      Long userId, Long competitionId, EntryTeamRepository entryTeamRepository) {
    EntryTeam entryTeam =
        entryTeamRepository
            .findByUser_IdAndCompetition_id(userId, competitionId)
            .orElseThrow(() -> BaseException.from(ErrorCode.ENTRY_TEAM_NOT_FOUND));

    return entryTeam;
  }
}
