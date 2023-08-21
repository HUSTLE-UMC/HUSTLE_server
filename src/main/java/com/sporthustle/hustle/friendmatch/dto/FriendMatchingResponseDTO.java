package com.sporthustle.hustle.friendmatch.dto;

import com.sporthustle.hustle.club.dto.MyClubsResponseDTO;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPostType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class FriendMatchingResponseDTO {
    Long friendMatchingPostId;
    FriendMatchingPostType category;
    String clubName;
    LocalDateTime date;
    String sportEventName;
    String locationAddress;
    String title;
    boolean isHost;
    //주요활동지역 필요


    List<AppliedTeamsDTO> appliedTeamsDTOS;
    List<MyClubsResponseDTO>myClubsResponseDTOS;
}
