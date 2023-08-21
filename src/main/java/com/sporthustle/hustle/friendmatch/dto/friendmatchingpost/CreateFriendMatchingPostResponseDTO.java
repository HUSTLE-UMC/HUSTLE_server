package com.sporthustle.hustle.friendmatch.dto.friendmatchingpost;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateFriendMatchingPostResponseDTO {

  private String message;

  private FriendMatchingPostResponseDTO data;
}
