package com.sporthustle.hustle.competition.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateCompetitionResponseDTO {

  private String message;

  private CompetitionResponseDTO data;
}
