package com.sporthustle.hustle.club.dto;

import com.sporthustle.hustle.club.entity.Club;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MyClubsResponseDTO {

  private Long id;

  private String name;

  public static MyClubsResponseDTO from(Club club) {
    return MyClubsResponseDTO.builder().id(club.getId()).name(club.getName()).build();
  }
}
