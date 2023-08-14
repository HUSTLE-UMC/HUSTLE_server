package com.sporthustle.hustle.sport.dto;

import com.sporthustle.hustle.sport.entity.SportEvent;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SportEventResponseDTO {

  private Long id;

  private String name;

  public static SportEventResponseDTO from(SportEvent sportEvent) {
    return SportEventResponseDTO.builder()
        .id(sportEvent.getId())
        .name(sportEvent.getName())
        .build();
  }
}
