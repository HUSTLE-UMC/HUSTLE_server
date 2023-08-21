package com.sporthustle.hustle.friendmatch.dto.friendmatchingrequest;

import lombok.Builder;

@Builder
public class UpdateFriendMatchingRequestStateResponseDTO {
  String message;
  private FriendMatchingRequestResponseDTO data;
}
