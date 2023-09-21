package com.sporthustle.hustle.club.dto;

import com.sporthustle.hustle.club.entity.JoinClubRequestType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateJoinClubRequestDTO {
    Long clubJoinRequestId;
    JoinClubRequestType joinClubRequestType;
}
