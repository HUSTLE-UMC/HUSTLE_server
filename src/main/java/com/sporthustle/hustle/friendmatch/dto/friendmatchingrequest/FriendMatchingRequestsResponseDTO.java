package com.sporthustle.hustle.friendmatch.dto.friendmatchingrequest;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FriendMatchingRequestsResponseDTO {
  private List<FriendMatchingRequestResponseDTO> friendMatchingRequestResponseDTOS;
}
