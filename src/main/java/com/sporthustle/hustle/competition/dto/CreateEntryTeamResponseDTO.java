package com.sporthustle.hustle.competition.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class CreateEntryTeamResponseDTO {

  private String message;

  private EntryTeamResponseDTO data;

}
