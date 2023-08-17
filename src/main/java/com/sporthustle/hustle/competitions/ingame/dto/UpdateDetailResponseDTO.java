package com.sporthustle.hustle.competitions.ingame.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateDetailResponseDTO {

  private String message;

  private DetailResponseDTO data;
}
