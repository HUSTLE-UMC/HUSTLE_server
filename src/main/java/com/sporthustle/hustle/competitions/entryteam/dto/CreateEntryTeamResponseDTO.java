package com.sporthustle.hustle.competitions.entryteam.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateEntryTeamResponseDTO {

  private String message;

  private EntryTeamResponseDTO data;
}
