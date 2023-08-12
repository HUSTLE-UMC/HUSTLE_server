package com.sporthustle.hustle.competition.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateCompetitionResponseDTO {

  private String message;

  private CompetitionResponseDTO data;
}
