package com.sporthustle.hustle.competitions.entryteam.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteEntryTeamResponseDTO {

  private String message;

  private EntryTeamResponseDTO data;

}
