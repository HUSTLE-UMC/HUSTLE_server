package com.sporthustle.hustle.competitions.ingame.dto;

import com.sporthustle.hustle.competitions.entryteam.dto.EntryTeamResponseDTO;
import com.sporthustle.hustle.competitions.ingame.entity.FinalRoundTeam;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FinalRoundTeamResponseDTO {

  private Long id;

  private Integer currentTournamentLevel;

  private EntryTeamResponseDTO entryTeam;

  public static FinalRoundTeamResponseDTO from(FinalRoundTeam finalRoundTeam) {
    EntryTeamResponseDTO entryTeamResponseDTO = EntryTeamResponseDTO.from(finalRoundTeam.getEntryTeam());

    return FinalRoundTeamResponseDTO.builder()
            .id(finalRoundTeam.getId())
            .currentTournamentLevel(finalRoundTeam.getCurrentTournamentLevel())
            .entryTeam(entryTeamResponseDTO)
            .build();
  }
}
