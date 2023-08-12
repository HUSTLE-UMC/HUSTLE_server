package com.sporthustle.hustle.club.dto;

import com.sporthustle.hustle.club.entity.ClubMember;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubMemberResponseDTO {

  private Long id;

  private String name;

  private Long point;

  public static ClubMemberResponseDTO from(ClubMember clubMember) {
    return ClubMemberResponseDTO.builder()
        .id(clubMember.getId())
        .name(clubMember.getUser().getName())
        .point(clubMember.getPoint())
        .build();
  }
}
