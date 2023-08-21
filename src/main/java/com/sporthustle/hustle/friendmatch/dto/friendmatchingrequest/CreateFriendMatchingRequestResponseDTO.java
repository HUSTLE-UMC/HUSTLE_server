package com.sporthustle.hustle.friendmatch.dto.friendmatchingrequest;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateFriendMatchingRequestResponseDTO {
    private String message;
    private FriendMatchingRequestResponseDTO data;
}
