package com.sporthustle.hustle.community.club.comment;

import com.sporthustle.hustle.club.ClubUtils;
import com.sporthustle.hustle.club.entity.Club;
import com.sporthustle.hustle.club.repository.ClubRepository;
import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.community.club.comment.dto.*;
import com.sporthustle.hustle.community.club.comment.entity.ClubPostComment;
import com.sporthustle.hustle.community.club.comment.entity.ClubPostCommentLike;
import com.sporthustle.hustle.community.club.comment.repository.ClubPostCommentLikeRepository;
import com.sporthustle.hustle.community.club.comment.repository.ClubPostCommentRepository;
import com.sporthustle.hustle.community.club.post.ClubPostUtils;
import com.sporthustle.hustle.community.club.post.entity.ClubPost;
import com.sporthustle.hustle.community.club.post.repository.ClubPostRepository;
import com.sporthustle.hustle.user.UserUtils;
import com.sporthustle.hustle.user.entity.User;
import com.sporthustle.hustle.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ClubPostCommentLikeService {

  private final ClubPostCommentLikeRepository clubPostCommentLikeRepository;
  private final ClubPostCommentRepository clubPostCommentRepository;
  private final UserRepository userRepository;
  private final ClubRepository clubRepository;
  private final ClubPostRepository clubPostRepository;

  @Transactional
  public GetClubPostCommentResponseDTO createClubPostCommentLike(
      Long userId, Long clubId, Long clubPostId, Long clubPostCommentId) {

    User user = UserUtils.getUserById(userId, userRepository);
    Club club = ClubUtils.getClubById(clubId, clubRepository);
    ClubPost clubPost = ClubPostUtils.getClubPostById(clubPostId, clubPostRepository);
    ClubPostComment clubPostComment =
        ClubPostCommentUtils.getClubPostCommentById(clubPostCommentId, clubPostCommentRepository);

    ClubUtils.validateUserIsMemberInClub(user, club);
    ClubPostCommentUtils.validateClubPostCommentInClubPost(clubPost, clubPostComment);
    validateUserIsNotWriter(user, clubPostComment);
    validateAlreadyCreated(user, clubPostComment);

    ClubPostCommentLike clubPostCommentLike =
        ClubPostCommentLike.builder().user(user).clubPostComment(clubPostComment).build();
    clubPostCommentLikeRepository.save(clubPostCommentLike);

    clubPostComment.getClubPostCommentLikeSet().add(clubPostCommentLike);
    clubPostComment.increaseLikeCount();
    clubPostCommentRepository.save(clubPostComment);

    return GetClubPostCommentResponseDTO.builder()
        .code("SUCCESS_CREATE_CLUB_POST_COMMENT_LIKE")
        .message("성공적으로 동아리 게시글 댓글을 추천했습니다.")
        .data(ClubPostCommentResponseDTO.from(clubPostComment, user))
        .build();
  }

  private void validateUserIsNotWriter(User user, ClubPostComment clubPostComment) {
    if (user.getId() == clubPostComment.getUser().getId()) {
      throw BaseException.from(ErrorCode.USER_MUST_NOT_OWNER);
    }
  }

  private void validateAlreadyCreated(User user, ClubPostComment clubPostComment) {
    Optional<ClubPostCommentLike> clubPostCommentLike =
        clubPostComment.getClubPostCommentLikeSet().stream()
            .filter(commentLike -> commentLike.getUser().getId() == user.getId())
            .findFirst();

    if (clubPostCommentLike.isPresent()) {
      throw BaseException.from(ErrorCode.CLUB_POST_COMMENT_LIKE_ALREADY_CREATED);
    }
  }

  @Transactional
  public GetClubPostCommentResponseDTO deleteClubPostCommentLike(
      Long userId, Long clubId, Long clubPostId, Long clubPostCommentId) {

    User user = UserUtils.getUserById(userId, userRepository);
    Club club = ClubUtils.getClubById(clubId, clubRepository);
    ClubPost clubPost = ClubPostUtils.getClubPostById(clubPostId, clubPostRepository);
    ClubPostComment clubPostComment =
        ClubPostCommentUtils.getClubPostCommentById(clubPostCommentId, clubPostCommentRepository);

    ClubUtils.validateUserIsMemberInClub(user, club);
    ClubPostCommentUtils.validateClubPostCommentInClubPost(clubPost, clubPostComment);
    validateUserIsNotWriter(user, clubPostComment);

    ClubPostCommentLike clubPostCommentLike =
        clubPostComment.getClubPostCommentLikeSet().stream()
            .filter(commentLike -> commentLike.getUser().getId() == user.getId())
            .findFirst()
            .orElseThrow(() -> BaseException.from(ErrorCode.CLUB_POST_COMMENT_LIKE_NOT_FOUND));

    clubPostComment.getClubPostCommentLikeSet().remove(clubPostCommentLike);
    clubPostComment.decreaseLikeCount();
    clubPostCommentRepository.save(clubPostComment);

    return GetClubPostCommentResponseDTO.builder()
        .code("SUCCESS_DELETE_CLUB_POST_COMMENT_LIKE")
        .message("성공적으로 동아리 게시글 댓글을 추천 취소했습니다.")
        .data(ClubPostCommentResponseDTO.from(clubPostComment, user))
        .build();
  }
}
