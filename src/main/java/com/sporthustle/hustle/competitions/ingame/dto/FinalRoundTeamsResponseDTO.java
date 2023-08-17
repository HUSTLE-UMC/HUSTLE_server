package com.sporthustle.hustle.competitions.ingame.dto;

import com.sporthustle.hustle.competitions.ingame.entity.FinalRoundTeam;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FinalRoundTeamsResponseDTO {

  public List<FinalRoundTeam> finalRoundTeams;
}
