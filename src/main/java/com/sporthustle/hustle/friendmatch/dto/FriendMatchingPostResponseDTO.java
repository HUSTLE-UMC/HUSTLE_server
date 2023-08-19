package com.sporthustle.hustle.friendmatch.dto;

import com.sporthustle.hustle.club.dto.ClubResponseDTO;
import com.sporthustle.hustle.club.entity.Club;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPostType;
import com.sporthustle.hustle.sport.dto.SportEventResponseDTO;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPost;
import com.sporthustle.hustle.sport.entity.SportEvent;
import com.sporthustle.hustle.university.dto.UniversityResponseDTO;
import com.sporthustle.hustle.university.entity.University;
import com.sporthustle.hustle.user.UserUtils;
import com.sporthustle.hustle.user.dto.FriendMatchingUserResponseDTO;
import com.sporthustle.hustle.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.geo.Point;
import java.time.LocalDateTime;

@Getter
@Builder
public class FriendMatchingPostResponseDTO{

    private Long id;
    private String title;
    private FriendMatchingPostType category;
    private String name;
    private String phoneNumber;
    private LocalDateTime date;
    private Point location;
    private String locationAddress;
    private FriendMatchingUserResponseDTO user;
    private ClubResponseDTO club;
    private SportEventResponseDTO sportEvent;

    public static FriendMatchingPostResponseDTO from(FriendMatchingPost friendMatchingPost) {


        SportEvent sportEvent = friendMatchingPost.getSportEvent();
        SportEventResponseDTO sportEventResponseDTO = SportEventResponseDTO.from(sportEvent);
        Club club = friendMatchingPost.getClub();
        ClubResponseDTO clubResponseDTO = ClubResponseDTO.from(club);
        User user = friendMatchingPost.getUser();
        FriendMatchingUserResponseDTO friendMatchingUserResponseDTO = FriendMatchingUserResponseDTO.from(user);

        return FriendMatchingPostResponseDTO.builder()
                .id(friendMatchingPost.getId())
                .title(friendMatchingPost.getTitle())
                .category(friendMatchingPost.getCategory())
                .name(friendMatchingPost.getName())
                .phoneNumber(friendMatchingPost.getPhoneNumber())
                .date(friendMatchingPost.getDate())
                .location(friendMatchingPost.getLocation())
                .locationAddress(friendMatchingPost.getLocationAddress())
                .user(friendMatchingUserResponseDTO)
                .sportEvent(sportEventResponseDTO)
                .club(clubResponseDTO)
                .build();
    }

    // Other methods, constructors, getters, setters, etc.
}
