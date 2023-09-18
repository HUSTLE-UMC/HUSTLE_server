package com.sporthustle.hustle.community.club.post;

import com.sporthustle.hustle.club.ClubUtils;
import com.sporthustle.hustle.club.entity.Club;
import com.sporthustle.hustle.club.repository.ClubRepository;
import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.community.club.comment.repository.ClubPostCommentRepository;
import com.sporthustle.hustle.community.club.post.dto.*;
import com.sporthustle.hustle.community.club.post.entity.ClubPost;
import com.sporthustle.hustle.community.club.post.repository.ClubPostRepository;
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
public class ClubPostService {

  private final ClubPostRepository clubPostRepository;
  private final ClubPostCommentRepository clubPostCommentRepository;
  private final UserRepository userRepository;
  private final ClubRepository clubRepository;

  @Transactional
  public CreateClubPostResponseDTO createClubPost(
      Long userId, Long clubId, CreateClubPostRequestDTO createClubPostRequestDTO) {

    User user = UserUtils.getUserById(userId, userRepository);
    Club club = ClubUtils.getClubById(clubId, clubRepository);

    validateUserIsMemberInClub(user, club);

    ClubPost clubPost =
        ClubPost.builder()
            .title(createClubPostRequestDTO.getTitle())
            .content(createClubPostRequestDTO.getContent())
            .user(user)
            .club(club)
            .build();

    clubPostRepository.save(clubPost);

    return CreateClubPostResponseDTO.builder()
        .code("SUCCESS_CREATE_CLUB_POST")
        .message("성공적으로 동아리 게시글을 생성했습니다.")
        .data(ClubPostResponseDTO.from(clubPost))
        .build();
  }

  private void validateUserIsMemberInClub(User user, Club club) {
    boolean isUserInClubMembers =
        user.getClubMembers().stream()
            .anyMatch(clubMember -> clubMember.getClub().getId() == club.getId());
    if (!isUserInClubMembers) {
      throw BaseException.from(ErrorCode.MEMBER_NOT_IN_CLUB);
    }
  }

  @Transactional
  public UpdateClubPostResponseDTO updateClubPost(
      Long userId,
      Long clubId,
      Long clubPostId,
      UpdateClubPostRequestDTO updateClubPostRequestDTO) {

    User user = UserUtils.getUserById(userId, userRepository);
    Club club = ClubUtils.getClubById(clubId, clubRepository);
    ClubPost clubPost = ClubPostUtils.getClubPostById(clubPostId, clubPostRepository);

    validateUserIsMemberInClub(user, club);
    validateUserIsWriter(user, clubPost);

    clubPost.update(updateClubPostRequestDTO.getTitle(), updateClubPostRequestDTO.getContent());

    clubPostRepository.save(clubPost);

    return UpdateClubPostResponseDTO.builder()
        .code("SUCCESS_UPDATE_CLUB_POST")
        .message("성공적으로 동아리 게시글을 수정했습니다.")
        .data(ClubPostResponseDTO.from(clubPost))
        .build();
  }

  private void validateUserIsWriter(User user, ClubPost clubPost) {
    if (user.getId() != clubPost.getUser().getId()) {
      throw BaseException.from(ErrorCode.USER_NOT_OWNER);
    }
  }

  @Transactional
  public DeleteClubPostResponseDTO deleteClubPost(Long userId, Long clubId, Long clubPostId) {

    User user = UserUtils.getUserById(userId, userRepository);
    Club club = ClubUtils.getClubById(clubId, clubRepository);
    ClubPost clubPost = ClubPostUtils.getClubPostById(clubPostId, clubPostRepository);

    validateUserIsMemberInClub(user, club);
    validateUserIsWriter(user, clubPost);

    clubPost.delete();
    clubPostRepository.save(clubPost);

    return DeleteClubPostResponseDTO.builder()
        .code("SUCCESS_DELETE_CLUB_POST")
        .message("성공적으로 동아리 게시글을 삭제했습니다.")
        .data(ClubPostResponseDTO.from(clubPost))
        .build();
  }

  @Transactional(readOnly = true)
  public ClubPostsResponseDTO getClubPosts(Long userId, Long clubId, Pageable pageable) {

    User user = UserUtils.getUserById(userId, userRepository);
    Club club = ClubUtils.getClubById(clubId, clubRepository);

    validateUserIsMemberInClub(user, club);

    Page<ClubPost> clubPosts = clubPostRepository.findAllByClub_IdOrderByIdDesc(clubId, pageable);
    Page<ClubPostResponseDTO> clubPostDTOs = clubPosts.map(ClubPostResponseDTO::from);

    return ClubPostsResponseDTO.builder()
        .code("SUCCESS_GET_CLUB_POSTS")
        .message("성공적으로 동아리 게시글 목록을 조회했습니다.")
        .data(clubPostDTOs.getContent())
        .count(clubPostDTOs.getNumberOfElements())
        .totalPage(clubPostDTOs.getTotalPages())
        .totalCount(clubPostDTOs.getTotalElements())
        .build();
  }

  @Transactional(readOnly = true)
  public GetClubPostResponseDTO getClubPost(Long userId, Long clubId, Long clubPostId) {

    User user = UserUtils.getUserById(userId, userRepository);
    Club club = ClubUtils.getClubById(clubId, clubRepository);
    ClubPost clubPost = ClubPostUtils.getClubPostById(clubPostId, clubPostRepository);

    validateUserIsMemberInClub(user, club);

    return GetClubPostResponseDTO.builder()
        .code("SUCCESS_GET_CLUB_POSTS")
        .message("성공적으로 동아리 게시글 목록을 조회했습니다.")
        .data(ClubPostResponseDTO.from(clubPost))
        .build();
  }
}
