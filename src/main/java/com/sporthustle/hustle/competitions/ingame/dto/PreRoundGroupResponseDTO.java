package com.sporthustle.hustle.competitions.ingame.dto;

import com.sporthustle.hustle.competitions.entryteam.dto.EntryTeamResponseDTO;
import com.sporthustle.hustle.competitions.ingame.entity.PreRoundGroup;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PreRoundGroupResponseDTO {

  private Long id;

  private String groupCategory;

  private Integer matchCount;

  private Integer winCount;

  private Integer loseCount;

  private Integer drawCount;

  private Integer scoreDifferenceCount;

  private EntryTeamResponseDTO entryTeam;

  public static PreRoundGroupResponseDTO from(PreRoundGroup preRoundGroup) {
    return PreRoundGroupResponseDTO.builder()
        .id(preRoundGroup.getId())
        .groupCategory(preRoundGroup.getGroupCategory())
        .matchCount(preRoundGroup.getMatchCount())
        .winCount(preRoundGroup.getWinCount())
        .loseCount(preRoundGroup.getLoseCount())
        .drawCount(preRoundGroup.getDrawCount())
        .scoreDifferenceCount(preRoundGroup.getScoreDifferenceCount())
        .entryTeam(EntryTeamResponseDTO.from(preRoundGroup.getEntryTeam()))
        .build();
  }
}
