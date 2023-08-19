package com.sporthustle.hustle.friendmatch;

import com.sporthustle.hustle.club.ClubUtils;
import com.sporthustle.hustle.club.entity.Club;
import com.sporthustle.hustle.club.repository.ClubRepository;
import com.sporthustle.hustle.friendmatch.dto.CreateFriendMatchingPostRequestDTO;
import com.sporthustle.hustle.friendmatch.dto.CreateFriendMatchingPostResponseDTO;
import com.sporthustle.hustle.friendmatch.dto.FriendMatchingPostResponseDTO;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPost;
import com.sporthustle.hustle.friendmatch.repository.FriendMatchingRepository;
import com.sporthustle.hustle.sport.SportUtils;
import com.sporthustle.hustle.sport.entity.SportEvent;
import com.sporthustle.hustle.sport.repository.SportEventRepository;
import com.sporthustle.hustle.user.UserUtils;
import com.sporthustle.hustle.user.entity.User;
import com.sporthustle.hustle.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FriendMatchingService {
    private final FriendMatchingRepository friendMatchingRepository;
    private final SportEventRepository sportEventRepository;
    private final UserRepository userRepository;
    private final ClubRepository clubRepository;

    @Transactional
    public CreateFriendMatchingPostResponseDTO createFriendMatchingPost(
            Long userId, CreateFriendMatchingPostRequestDTO createFriendMatchingPostRequestDTO) {

        FriendMatchingPost friendMatchingPost =
                FriendMatchingPost.builder()
                        .title(createFriendMatchingPostRequestDTO.getTitle())
                        .category(createFriendMatchingPostRequestDTO.getCategory())
                        .name(createFriendMatchingPostRequestDTO.getName())
                        .phoneNumber(createFriendMatchingPostRequestDTO.getPhoneNumber())
                        .date(createFriendMatchingPostRequestDTO.getDate())
                        .location(createFriendMatchingPostRequestDTO.getLocation())
                        .locationAddress(createFriendMatchingPostRequestDTO.getLocationAddress())
                        .build();

        User user = UserUtils.getUserById(userId, userRepository);
        friendMatchingPost.setUser(user);

        Club club = ClubUtils.getClubById(createFriendMatchingPostRequestDTO.getClubId(),clubRepository);
        friendMatchingPost.setClub(club);

        SportEvent sportEvent = SportUtils.getSportEventById(createFriendMatchingPostRequestDTO.getSportEventId(), sportEventRepository);
        friendMatchingPost.setSportEvent(sportEvent);

        friendMatchingRepository.save(friendMatchingPost);
        FriendMatchingPostResponseDTO friendMatchingPostResponseDTO = FriendMatchingPostResponseDTO.from(friendMatchingPost);

        return CreateFriendMatchingPostResponseDTO.builder()
                .message("교류전 게시글을 생성했습니다.")
                .data(friendMatchingPostResponseDTO)
                .build();
    }





}
