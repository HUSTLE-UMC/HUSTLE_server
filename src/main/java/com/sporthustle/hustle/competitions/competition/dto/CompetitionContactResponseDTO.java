package com.sporthustle.hustle.competitions.competition.dto;

import com.sporthustle.hustle.competitions.competition.entity.CompetitionContact;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompetitionContactResponseDTO {

  private Long id;

  private String name;

  private String phoneNumber;

  public static CompetitionContactResponseDTO from(CompetitionContact competitionContact) {
    return CompetitionContactResponseDTO.builder()
        .id(competitionContact.getId())
        .name(competitionContact.getName())
        .phoneNumber(competitionContact.getPhoneNumber())
        .build();
  }
}
