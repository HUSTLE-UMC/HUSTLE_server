package com.sporthustle.hustle.competition.dto;

import com.sporthustle.hustle.competition.entity.competition.CompetitionContact;
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
