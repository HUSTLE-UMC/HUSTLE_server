package com.sporthustle.hustle.community.club.post.dto;

import com.sporthustle.hustle.community.club.post.entity.ClubPost;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubPostResponseDTO {
  private Long id;
  private String title;
  private String content;
  private ClubPostUserResponseDTO user;
  private ClubPostUserClubResponseDTO club;

  public static ClubPostResponseDTO from(ClubPost clubPost) {
    return ClubPostResponseDTO.builder()
        .id(clubPost.getId())
        .title(clubPost.getTitle())
        .content(clubPost.getContent())
        .user(ClubPostUserResponseDTO.from(clubPost.getUser()))
        .club(ClubPostUserClubResponseDTO.from(clubPost.getClub()))
        .build();
  }
}
