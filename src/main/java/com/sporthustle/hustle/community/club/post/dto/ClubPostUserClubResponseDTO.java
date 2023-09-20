package com.sporthustle.hustle.community.club.post.dto;

import com.sporthustle.hustle.club.entity.Club;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubPostUserClubResponseDTO {
  private Long id;

  public static ClubPostUserClubResponseDTO from(Club club) {
    return ClubPostUserClubResponseDTO.builder().id(club.getId()).build();
  }
}
