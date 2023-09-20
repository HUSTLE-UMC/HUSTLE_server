package com.sporthustle.hustle.community.club.comment;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.community.club.comment.entity.ClubPostComment;
import com.sporthustle.hustle.community.club.comment.repository.ClubPostCommentRepository;
import com.sporthustle.hustle.community.club.post.entity.ClubPost;

public class ClubPostCommentUtils {

  public static ClubPostComment getClubPostCommentById(
      Long id, ClubPostCommentRepository clubPostCommentRepository) {
    return clubPostCommentRepository
        .findById(id)
        .orElseThrow(() -> BaseException.from(ErrorCode.CLUB_POST_COMMENT_NOT_FOUND));
  }

  public static void validateClubPostCommentInClubPost(
      ClubPost clubPost, ClubPostComment clubPostComment) {
    boolean isClubPostCommentInClubPost =
        clubPost.getComments().stream()
            .anyMatch(comment -> comment.getId() == clubPostComment.getId());
    if (!isClubPostCommentInClubPost) {
      throw BaseException.from(ErrorCode.CLUB_POST_COMMENT_NOT_IN_CLUB_POST);
    }
  }
}
