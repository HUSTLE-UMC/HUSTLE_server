package com.sporthustle.hustle.community.club.post.dto;

import com.sporthustle.hustle.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubPostUserResponseDTO {
  private Long id;
  private String name;

  public static ClubPostUserResponseDTO from(User user) {
    return ClubPostUserResponseDTO.builder().id(user.getId()).name(user.getName()).build();
  }
}
