package com.sporthustle.hustle.friendmatch.dto.friendmatchingrequest;

import com.sporthustle.hustle.club.dto.ClubResponseDTO;
import com.sporthustle.hustle.club.entity.Club;
import com.sporthustle.hustle.friendmatch.dto.friendmatchingpost.FriendMatchingPostResponseDTO;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPost;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingRequest;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingRequestType;
import com.sporthustle.hustle.user.dto.FriendMatchingUserResponseDTO;
import com.sporthustle.hustle.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import org.locationtech.jts.geom.Point;

@Getter
@Builder
public class FriendMatchingRequestResponseDTO {

  private Long id;
  private FriendMatchingRequestType type;
  private Point location;
  private String locationAddress;
  private String name;
  private String phoneNumber;
  private FriendMatchingUserResponseDTO user;
  private ClubResponseDTO club;
  private FriendMatchingPostResponseDTO friendMatchingPost;

  public static FriendMatchingRequestResponseDTO from(FriendMatchingRequest friendMatchingRequest) {

    Club club = friendMatchingRequest.getClub();
    ClubResponseDTO clubResponseDTO = ClubResponseDTO.from(club);

    User user = friendMatchingRequest.getUser();
    FriendMatchingUserResponseDTO friendMatchingUserResponseDTO =
        FriendMatchingUserResponseDTO.from(user);

    FriendMatchingPost friendMatchingPost = friendMatchingRequest.getFriendMatchingPost();
    FriendMatchingPostResponseDTO friendMatchingPostResponseDTO =
        FriendMatchingPostResponseDTO.from(friendMatchingPost);

    return FriendMatchingRequestResponseDTO.builder()
        .id(friendMatchingRequest.getId())
        .type(friendMatchingRequest.getType())
        .location(friendMatchingRequest.getLocation())
        .locationAddress(friendMatchingRequest.getLocationAddress())
        .name(friendMatchingRequest.getName())
        .phoneNumber(friendMatchingRequest.getPhoneNumber())
        .user(friendMatchingUserResponseDTO)
        .club(clubResponseDTO)
        .friendMatchingPost(friendMatchingPostResponseDTO)
        .build();
  }
}
