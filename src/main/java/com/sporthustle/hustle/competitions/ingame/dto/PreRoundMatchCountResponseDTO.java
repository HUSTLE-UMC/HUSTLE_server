package com.sporthustle.hustle.competitions.ingame.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PreRoundMatchCountResponseDTO {

  private String message;

  private int data;
}
