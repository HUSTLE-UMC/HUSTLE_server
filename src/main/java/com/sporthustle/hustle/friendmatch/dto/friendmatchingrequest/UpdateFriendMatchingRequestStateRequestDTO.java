package com.sporthustle.hustle.friendmatch.dto.friendmatchingrequest;

import com.sporthustle.hustle.friendmatch.entity.FriendMatchingRequestType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateFriendMatchingRequestStateRequestDTO {
  Long friendMatchingRequestId;
  FriendMatchingRequestType friendMatchingRequestType;
}
