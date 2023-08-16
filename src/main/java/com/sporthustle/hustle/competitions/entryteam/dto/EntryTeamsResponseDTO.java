package com.sporthustle.hustle.competitions.entryteam.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class EntryTeamsResponseDTO {

  private List<EntryTeamResponseDTO> entryTeams;
}
