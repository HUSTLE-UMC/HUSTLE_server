package com.sporthustle.hustle.competitions.competition.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateCompetitionResponseDTO {

  private String message;

  private CompetitionResponseDTO data;
}
