package com.sporthustle.hustle.competitions.match.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateMatchResultPostResponseDTO {

  private String message;

  private MatchResultPostResponseDTO data;
}
