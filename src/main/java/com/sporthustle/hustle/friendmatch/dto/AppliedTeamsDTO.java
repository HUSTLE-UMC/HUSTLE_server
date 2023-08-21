package com.sporthustle.hustle.friendmatch.dto;

import com.sporthustle.hustle.friendmatch.entity.FriendMatchingRequestType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AppliedTeamsDTO {

    Long friendMatchingRequestId;
    FriendMatchingRequestType type;
    String locationAddress;
    String name; //대표자명
    String phoneNumber;

}
