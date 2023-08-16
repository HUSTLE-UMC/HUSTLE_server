package com.sporthustle.hustle.competition.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteEntryTeamResponseDTO {

  private String message;

  private EntryTeamResponseDTO data;

}
