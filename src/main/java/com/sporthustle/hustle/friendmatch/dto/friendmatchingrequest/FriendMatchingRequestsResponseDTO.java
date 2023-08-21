package com.sporthustle.hustle.friendmatch.dto.friendmatchingrequest;
import lombok.Builder;
import lombok.Getter;
import java.util.List;
@Builder
@Getter
public class FriendMatchingRequestsResponseDTO {
    private List<FriendMatchingRequestResponseDTO> FriendMatchingRequests;
}
