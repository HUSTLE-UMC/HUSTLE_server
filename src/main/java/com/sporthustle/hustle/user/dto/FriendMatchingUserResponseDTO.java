package com.sporthustle.hustle.user.dto;

import com.sporthustle.hustle.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FriendMatchingUserResponseDTO {
  private Long id;

  public static FriendMatchingUserResponseDTO from(User user) {
    return FriendMatchingUserResponseDTO.builder().id(user.getId()).build();
  }
}
