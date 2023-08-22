package com.sporthustle.hustle.friendmatch.dto.friendmatchingrequest;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateFriendMatchingRequestStateRequestDTO {
  Long friendMatchingRequestId;
  String friendMatchingRequestType;
}
