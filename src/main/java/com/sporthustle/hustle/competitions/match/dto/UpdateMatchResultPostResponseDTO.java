package com.sporthustle.hustle.competitions.match.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateMatchResultPostResponseDTO {

  private String message;

  private MatchResultPostResponseDTO data;
}
