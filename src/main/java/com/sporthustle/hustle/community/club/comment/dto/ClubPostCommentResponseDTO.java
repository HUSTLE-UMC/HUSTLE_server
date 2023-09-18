package com.sporthustle.hustle.community.club.comment.dto;

import com.sporthustle.hustle.community.club.comment.entity.ClubPostComment;
import com.sporthustle.hustle.community.club.post.dto.ClubPostUserResponseDTO;
import com.sporthustle.hustle.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubPostCommentResponseDTO {

  private Long id;
  private String content;
  private Long likeCount;
  private ClubPostUserResponseDTO user;
  private Boolean isLiked;

  public static ClubPostCommentResponseDTO from(ClubPostComment clubPostComment, User viewer) {
    return ClubPostCommentResponseDTO.builder()
        .id(clubPostComment.getId())
        .content(clubPostComment.getContent())
        .likeCount(clubPostComment.getLikeCount())
        .user(ClubPostUserResponseDTO.from(clubPostComment.getUser()))
        .isLiked(clubPostComment.hasUserInCommentLikeSet(viewer))
        .build();
  }
}
