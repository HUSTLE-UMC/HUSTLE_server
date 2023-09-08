package com.sporthustle.hustle.friendmatch;

import com.sporthustle.hustle.club.ClubUtils;
import com.sporthustle.hustle.club.entity.Club;
import com.sporthustle.hustle.club.repository.ClubRepository;
import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.friendmatch.dto.friendmatchingpost.CreateFriendMatchingPostRequestDTO;
import com.sporthustle.hustle.friendmatch.dto.friendmatchingpost.CreateFriendMatchingPostResponseDTO;
import com.sporthustle.hustle.friendmatch.dto.friendmatchingpost.FriendMatchingPostResponseDTO;
import com.sporthustle.hustle.friendmatch.dto.friendmatchingpost.FriendMatchingPostsResponseDTO;
import com.sporthustle.hustle.friendmatch.dto.friendmatchingrequest.*;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPost;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPostType;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingRequest;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingRequestType;
import com.sporthustle.hustle.friendmatch.repository.FriendMatchingPostRepository;
import com.sporthustle.hustle.friendmatch.repository.FriendMatchingRequestRepository;
import com.sporthustle.hustle.sport.SportUtils;
import com.sporthustle.hustle.sport.entity.SportEvent;
import com.sporthustle.hustle.sport.repository.SportEventRepository;
import com.sporthustle.hustle.user.UserUtils;
import com.sporthustle.hustle.user.entity.User;
import com.sporthustle.hustle.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FriendMatchingService {
  private final FriendMatchingPostRepository friendMatchingPostRepository;
  private final SportEventRepository sportEventRepository;
  private final UserRepository userRepository;
  private final ClubRepository clubRepository;
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
            .startDate(createFriendMatchingPostRequestDTO.getStartDate())
            .location(createFriendMatchingPostRequestDTO.getLocation())
            .locationAddress(createFriendMatchingPostRequestDTO.getLocationAddress())
            .build();
    User user = UserUtils.getUserById(userId, userRepository);
    friendMatchingPost.setUser(user);

    Club club =
        ClubUtils.getClubById(createFriendMatchingPostRequestDTO.getClubId(), clubRepository);
    friendMatchingPost.setClub(club);

    SportEvent sportEvent =
        SportUtils.getSportEventById(
            createFriendMatchingPostRequestDTO.getSportEventId(), sportEventRepository);
    friendMatchingPost.setSportEvent(sportEvent);

    friendMatchingPostRepository.save(friendMatchingPost);
    FriendMatchingPostResponseDTO friendMatchingPostResponseDTO =
        FriendMatchingPostResponseDTO.from(friendMatchingPost);

    return CreateFriendMatchingPostResponseDTO.builder()
        .code("SUCCESS_CREATE_FRIEND_MATCHING_POST")
        .message("교류전 게시글을 생성했습니다.")
        .data(friendMatchingPostResponseDTO)
        .build();
  }

  @Transactional(readOnly = true)
  public FriendMatchingPostsResponseDTO getFriendMatchingPostsByType(
      Long sportEventID, FriendMatchingPostType type, Pageable pageable) {
    SportEvent sportEvent = SportUtils.getSportEventById(sportEventID, sportEventRepository);

    Page<FriendMatchingPost> friendMatchingPosts = Page.empty();
    switch (type) {
      case INVITE:
        friendMatchingPosts =
            friendMatchingPostRepository.findByCategoryAndSportEventOrderByStartDateAsc(
                FriendMatchingPostType.INVITE, sportEvent, pageable);
        break;
      case REQUEST:
        friendMatchingPosts =
            friendMatchingPostRepository.findByCategoryAndSportEventOrderByStartDateAsc(
                FriendMatchingPostType.REQUEST, sportEvent, pageable);
        break;
      default:
        throw new IllegalArgumentException("Invalid FriendMatchingPostType");
    }

    Page<FriendMatchingPostResponseDTO> friendMatchingPostResponseDTOs =
        friendMatchingPosts.map(FriendMatchingPostResponseDTO::from);

    return FriendMatchingPostsResponseDTO.builder()
        .code("SUCCESS_GET_FRIEND_MATCHING_POSTS_BY_TYPE")
        .message("성공적으로 교류전 목록을 조회했습니다.")
        .count(friendMatchingPostResponseDTOs.getNumberOfElements())
        .totalPage(friendMatchingPostResponseDTOs.getTotalPages())
        .totalCount(friendMatchingPostResponseDTOs.getTotalElements())
        .data(friendMatchingPostResponseDTOs.getContent())
        .build();
  }

  @Transactional
  public CreateFriendMatchingRequestResponseDTO createFriendMatchingRequest(
      Long matchId,
      Long userId,
      Long clubId,
      CreateFriendMatchingRequestRequestDTO createFriendMatchingRequestRequestDTO) {

    FriendMatchingPost friendMatchingPost =
        FriendMatchingUtils.getFriendMatchingPostById(matchId, friendMatchingPostRepository);
    Club club = ClubUtils.getClubById(clubId, clubRepository);
    User user = UserUtils.getUserById(userId, userRepository);

    FriendMatchingRequest friendMatchingRequest =
        FriendMatchingRequest.builder()
            .phoneNumber(createFriendMatchingRequestRequestDTO.getPhoneNumber())
            .name(createFriendMatchingRequestRequestDTO.getName())
            .locationAddress(createFriendMatchingRequestRequestDTO.getLocationAddress())
            .location(createFriendMatchingRequestRequestDTO.getLocation())
            .type(createFriendMatchingRequestRequestDTO.getType())
            .build();
    friendMatchingRequest.setUser(user);
    friendMatchingRequest.setClub(club);
    friendMatchingRequest.setFriendMatchingPost(friendMatchingPost);

    friendMatchingRequestRepository.save(friendMatchingRequest);

    FriendMatchingRequestResponseDTO friendMatchingRequestResponseDTO =
        FriendMatchingRequestResponseDTO.from(friendMatchingRequest);

    return CreateFriendMatchingRequestResponseDTO.builder()
        .code("CREATE_SUCCESS_FRIEND_MATCHING_REQUEST")
        .message("성공적으로 교류전에 요청하였습니다.")
        .data(friendMatchingRequestResponseDTO)
        .build();
  }

  @Transactional
  public UpdateFriendMatchingRequestResponseDTO updateFriendMatchingRequest(
      Long userId,
      Long friendMatchingPostId,
      UpdateFriendMatchingRequestStateRequestDTO updateFriendMatchingRequestStateRequestDTO) {

    FriendMatchingRequest friendMatchingRequest =
        FriendMatchingUtils.getFriendMatchingRequestById(
            updateFriendMatchingRequestStateRequestDTO.getFriendMatchingRequestId(),
            friendMatchingRequestRepository);
    FriendMatchingPost friendMatchingPost =
        FriendMatchingUtils.getFriendMatchingPostById(
            friendMatchingPostId, friendMatchingPostRepository);
    validateFriendMatchingPostOwner(friendMatchingPost, userId);
    friendMatchingRequest.updateType(
        FriendMatchingRequestType.valueOf(
            updateFriendMatchingRequestStateRequestDTO.getFriendMatchingRequestType()));

    FriendMatchingRequestResponseDTO friendMatchingRequestResponseDTO =
        FriendMatchingRequestResponseDTO.from(friendMatchingRequest);

    return UpdateFriendMatchingRequestResponseDTO.builder()
        .code("SUCCESS_UPDATE_FRIEND_MATCHING_REQUEST")
        .message("성공적으로 교류전 요청을 수정했습니다.")
        .data(friendMatchingRequestResponseDTO)
        .build();
  }

  @Transactional
  public FriendMatchingRequestsResponseDTO getFriendMatchingRequests(Long matchId, Long userId) {
    FriendMatchingPost friendMatchingPost =
        FriendMatchingUtils.getFriendMatchingPostById(matchId, friendMatchingPostRepository);
    validateFriendMatchingPostOwner(friendMatchingPost, userId);

    List<FriendMatchingRequest> friendMatchingRequests =
        friendMatchingRequestRepository.findAllByFriendMatchingPost(friendMatchingPost);
    List<FriendMatchingRequestResponseDTO> friendMatchingRequestResponseDTOS =
        friendMatchingRequests.stream()
            .map(FriendMatchingRequestResponseDTO::from)
            .collect(Collectors.toList());

    return FriendMatchingRequestsResponseDTO.builder()
        .code("SUCCESS_GET_FRIEND_MATCHING_REQUESTS")
        .message("성공적으로 교류전 요청 목록을 조회했습니다.")
        .data(friendMatchingRequestResponseDTOS)
        .build();
  }

  private void validateFriendMatchingPostOwner(FriendMatchingPost friendMatchingPost, Long userId) {
    User user = friendMatchingPost.getUser();
    if (user.getId() != userId) {
      throw BaseException.from(ErrorCode.USER_NOT_OWNER);
    }
  }
}
