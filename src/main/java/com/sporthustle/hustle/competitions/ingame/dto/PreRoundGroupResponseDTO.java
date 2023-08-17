package com.sporthustle.hustle.competitions.ingame.dto;

import com.sporthustle.hustle.competitions.entryteam.dto.EntryTeamResponseDTO;
import com.sporthustle.hustle.competitions.ingame.entity.PreRoundGroup;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PreRoundGroupResponseDTO {

  private Long id;

  private String name;

  private EntryTeamResponseDTO entryTeam;

  public static PreRoundGroupResponseDTO from(PreRoundGroup preRoundGroup) {
    EntryTeamResponseDTO entryTeamResponseDTO = EntryTeamResponseDTO.from(preRoundGroup.getEntryTeam());

    return PreRoundGroupResponseDTO.builder()
            .name(preRoundGroup.getName())
            .entryTeam(entryTeamResponseDTO)
            .build();
  }
}
