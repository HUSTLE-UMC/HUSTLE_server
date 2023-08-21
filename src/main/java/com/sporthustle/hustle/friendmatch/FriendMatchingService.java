package com.sporthustle.hustle.friendmatch;

import com.sporthustle.hustle.club.ClubUtils;
import com.sporthustle.hustle.club.dto.MyClubsResponseDTO;
import com.sporthustle.hustle.club.entity.Club;
import com.sporthustle.hustle.club.entity.ClubMember;
import com.sporthustle.hustle.club.repository.ClubMemberRepository;
import com.sporthustle.hustle.club.repository.ClubRepository;
import com.sporthustle.hustle.friendmatch.dto.*;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPost;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPostType;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingRequest;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingRequestType;
import com.sporthustle.hustle.friendmatch.repository.FriendMatchingRepository;
import com.sporthustle.hustle.friendmatch.repository.FriendMatchingRequestRepository;
import com.sporthustle.hustle.sport.SportUtils;
import com.sporthustle.hustle.sport.entity.SportEvent;
import com.sporthustle.hustle.sport.repository.SportEventRepository;
import com.sporthustle.hustle.user.UserUtils;
import com.sporthustle.hustle.user.entity.User;
import com.sporthustle.hustle.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FriendMatchingService {
    private final FriendMatchingRepository friendMatchingRepository;
    private final SportEventRepository sportEventRepository;
    private final UserRepository userRepository;
    private final ClubRepository clubRepository;
    private final ClubMemberRepository clubMemberRepository;
    private final FriendMatchingRequestRepository friendMatchingRequestRepository;


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


    @Transactional
    public FriendMatchingResponseDTO getRequests(Long matchId, Long userId) {
        Optional<FriendMatchingPost> match = friendMatchingRepository.findById(matchId);

        List<AppliedTeamsDTO> appliedTeamsDTOList = new ArrayList<>();
        List<MyClubsResponseDTO>myClubsResponseDTOS = new ArrayList<>();
        boolean isHost = checkUser(matchId,userId);

        if (isHost) {
            List<FriendMatchingRequest> requests = friendMatchingRequestRepository.findAllByFriendMatchingPost(match.get());
            requests.stream()
                    .map(
                            matchRequest ->
                                    appliedTeamsDTOList.add(
                                    AppliedTeamsDTO.builder()
                                            .friendMatchingRequestId(matchRequest.getFriendMatchingPost().getId())
                                            .type(matchRequest.getType())
                                            .locationAddress(matchRequest.getLocationAddress())
                                            .name(matchRequest.getName())
                                            .phoneNumber(matchRequest.getPhoneNumber())
                                            .locationAddress(matchRequest.getLocationAddress())
                                            .build()
                                    )
                    ).collect(Collectors.toList());

        }
        else{
            List<ClubMember>clubs = clubMemberRepository.findAllByUser_id(userId);
            clubs.stream()
                    .map(
                            c ->
                                    myClubsResponseDTOS.add(
                                            MyClubsResponseDTO.builder()
                                                    .id(c.getClub().getId())
                                                    .name(c.getClub().getName())
                                            .build()
                                    )
                    ).collect(Collectors.toList());

            }



        return FriendMatchingResponseDTO.builder()
                .friendMatchingPostId(match.get().getId())
                .category(match.get().getCategory())
                .clubName(match.get().getName())
                .date(match.get().getDate())
                .sportEventName(match.get().getSportEvent().getName())
                .locationAddress(match.get().getLocationAddress())
                .title(match.get().getTitle())
                .isHost(isHost)
                .appliedTeamsDTOS(appliedTeamsDTOList)
                .myClubsResponseDTOS(myClubsResponseDTOS)
                .build();

    }


    @Transactional
    public void updateRequests(UpdateStateRequestDTO updateStateRequestDTO) {

        Optional<FriendMatchingRequest> friendMatchingRequest =
                friendMatchingRequestRepository.findById(updateStateRequestDTO.getRequestId());

        friendMatchingRequest.get()
                .updateType(FriendMatchingRequestType.valueOf(updateStateRequestDTO.getFriendMatchingRequestType()));

    }

    public boolean checkUser(Long matchId, Long userId) {
        Optional<FriendMatchingPost> match = friendMatchingRepository.findById(matchId);

        return (match.get().getUser().getId() == userId);
    }

    @Transactional
    public ApplyResponseDTO applyFriendMatching(Long matchId, Long userId, ApplyRequestDTO applyRequestDTO) {
            Optional<FriendMatchingPost> friendMatchingPost = friendMatchingRepository.findById(matchId);
            Optional<User> user = userRepository.findById(userId);
            Optional<Club> club = clubRepository.findByName(applyRequestDTO.getName());

            FriendMatchingRequest friendMatchingRequest =
                    FriendMatchingRequest.builder()
                    //.location()    //Point?
                    .locationAddress(applyRequestDTO.getLocation())
                    .name(applyRequestDTO.getName())
                    .phoneNumber(applyRequestDTO.getPhoneNumber())
                    .friendMatchingPost(friendMatchingPost.get())
                    .user(user.get())
                    .club(club.get())
                    .build();

            friendMatchingRequestRepository.save(friendMatchingRequest);

            String message;
            if(FriendMatchingPostType.valueOf(applyRequestDTO.getCategory())==FriendMatchingPostType.REQUEST){
                message = "요청이 완료되었습니다!";
            }
            else{
                message = "초청이 완료되었습니다!";
            }

            return ApplyResponseDTO.builder().message(message).build();
    }
}
