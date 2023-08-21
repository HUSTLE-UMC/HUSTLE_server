package com.sporthustle.hustle.competitions.entryteam.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EntryTeamsResponseDTO {

  private List<EntryTeamResponseDTO> entryTeams;
}
