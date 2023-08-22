package com.sporthustle.hustle.competitions.match.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateMatchResultPostResponseDTO {

  private String message;

  private List<MatchResultPostResponseDTO> data;
}
