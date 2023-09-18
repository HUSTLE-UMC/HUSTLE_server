package com.sporthustle.hustle.community.club.comment;

import com.sporthustle.hustle.club.ClubUtils;
import com.sporthustle.hustle.club.entity.Club;
import com.sporthustle.hustle.club.repository.ClubRepository;
import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.community.club.comment.dto.*;
import com.sporthustle.hustle.community.club.comment.entity.ClubPostComment;
import com.sporthustle.hustle.community.club.comment.repository.ClubPostCommentRepository;
import com.sporthustle.hustle.community.club.post.ClubPostUtils;
import com.sporthustle.hustle.community.club.post.entity.ClubPost;
import com.sporthustle.hustle.community.club.post.repository.ClubPostRepository;
import com.sporthustle.hustle.user.UserUtils;
import com.sporthustle.hustle.user.entity.User;
import com.sporthustle.hustle.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClubPostCommentService {

  private final ClubPostCommentRepository clubPostCommentRepository;
  private final UserRepository userRepository;
  private final ClubRepository clubRepository;
  private final ClubPostRepository clubPostRepository;

  @Transactional
  public CreateClubPostCommentResponseDTO createClubPostComment(Long userId, Long clubId, Long clubPostId, CreateClubPostCommentRequestDTO createClubPostCommentRequestDTO) {

    User user = UserUtils.getUserById(userId, userRepository);
    Club club = ClubUtils.getClubById(clubId, clubRepository);
    ClubPost clubPost = ClubPostUtils.getClubPostById(clubPostId, clubPostRepository);

    validateUserIsMemberInClub(user, club);

    ClubPostComment clubPostComment = ClubPostComment.builder()
            .content(createClubPostCommentRequestDTO.getContent())
            .user(user)
            .clubPost(clubPost)
            .build();

    clubPostCommentRepository.save(clubPostComment);

    return CreateClubPostCommentResponseDTO.builder()
            .code("SUCCESS_CREATE_CLUB_POST_COMMENT")
            .message("성공적으로 동아리 게시글 댓글을 생성했습니다.")
            .data(ClubPostCommentResponseDTO.from(clubPostComment, user))
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

  private void validateUserIsWriter(User user, ClubPostComment clubPostComment) {
    if (user.getId() != clubPostComment.getUser().getId()) {
      throw BaseException.from(ErrorCode.USER_NOT_OWNER);
    }
  }

  @Transactional
  public UpdateClubPostCommentResponseDTO updateClubPostComment(Long userId, Long clubId, Long clubPostId, Long clubPostCommentId, UpdateClubPostCommentRequestDTO updateClubPostCommentRequestDTO) {

    User user = UserUtils.getUserById(userId, userRepository);
    Club club = ClubUtils.getClubById(clubId, clubRepository);
    ClubPost clubPost = ClubPostUtils.getClubPostById(clubPostId, clubPostRepository);
    ClubPostComment clubPostComment = ClubPostCommentUtils.getClubPostCommentById(clubPostCommentId, clubPostCommentRepository);

    validateUserIsMemberInClub(user, club);
    validateClubPostCommentInClubPost(clubPost, clubPostComment);
    validateUserIsWriter(user, clubPostComment);

    clubPostComment.update(updateClubPostCommentRequestDTO.getContent());

    clubPostCommentRepository.save(clubPostComment);

    return UpdateClubPostCommentResponseDTO.builder()
            .code("SUCCESS_UPDATE_CLUB_POST_COMMENT")
            .message("성공적으로 동아리 게시글 댓글을 수정했습니다.")
            .data(ClubPostCommentResponseDTO.from(clubPostComment, user))
            .build();
  }

  private void validateClubPostCommentInClubPost(ClubPost clubPost, ClubPostComment clubPostComment) {
    boolean isClubPostCommentInClubPost =
            clubPost.getComments().stream()
                    .anyMatch(comment -> comment.getId() == clubPostComment.getId());
    if (!isClubPostCommentInClubPost) {
      throw BaseException.from(ErrorCode.CLUB_POST_COMMENT_NOT_IN_CLUB_POST);
    }
  }

  @Transactional
  public DeleteClubPostCommentResponseDTO deleteClubPostComment(Long userId, Long clubId, Long clubPostId, Long clubPostCommentId) {

    User user = UserUtils.getUserById(userId, userRepository);
    Club club = ClubUtils.getClubById(clubId, clubRepository);
    ClubPost clubPost = ClubPostUtils.getClubPostById(clubPostId, clubPostRepository);
    ClubPostComment clubPostComment = ClubPostCommentUtils.getClubPostCommentById(clubPostCommentId, clubPostCommentRepository);

    validateUserIsMemberInClub(user, club);
    validateClubPostCommentInClubPost(clubPost, clubPostComment);
    validateUserIsWriter(user, clubPostComment);

    clubPostComment.delete();

    clubPostCommentRepository.save(clubPostComment);

    return DeleteClubPostCommentResponseDTO.builder()
            .code("SUCCESS_DELETE_CLUB_POST_COMMENT")
            .message("성공적으로 동아리 게시글 댓글을 삭제했습니다.")
            .data(ClubPostCommentResponseDTO.from(clubPostComment, user))
            .build();
  }

  @Transactional(readOnly = true)
  public ClubPostCommentsResponseDTO getClubPostComments(Long userId, Long clubId, Long clubPostId, CommentSortType commentSortType) {

    User user = UserUtils.getUserById(userId, userRepository);
    Club club = ClubUtils.getClubById(clubId, clubRepository);

    validateUserIsMemberInClub(user, club);

    List<ClubPostComment> clubPostComments = null;

    switch (commentSortType) {
      case NEW:
        clubPostComments = clubPostCommentRepository.findAllByClubPost_IdOrderByIdDesc(clubPostId);
        break;
      case LIKE_COUNT:
        clubPostComments = clubPostCommentRepository.findAllByClubPost_IdOrderByLikeCountDesc(clubPostId);
        break;
      default:
        throw new IllegalArgumentException("commentSortType 해당되는 값이 없습니다.");
    }

    List<ClubPostCommentResponseDTO> clubPostCommentResponseDTOs = clubPostComments.stream()
            .map(clubPostComment -> ClubPostCommentResponseDTO.from(clubPostComment, user))
            .collect(Collectors.toList());

    return ClubPostCommentsResponseDTO.builder()
            .code("SUCCESS_DELETE_CLUB_POST_COMMENT")
            .message("성공적으로 동아리 게시글 댓글을 삭제했습니다.")
            .data(clubPostCommentResponseDTOs)
            .build();
  }

}
