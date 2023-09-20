package com.sporthustle.hustle.community.club.comment.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.user.entity.User;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ClubPostCommentLike")
@Entity
public class ClubPostCommentLike extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "club_post_comment_id", nullable = false)
  private ClubPostComment clubPostComment;

  @Builder
  private ClubPostCommentLike(User user, ClubPostComment clubPostComment) {
    this.user = user;
    this.clubPostComment = clubPostComment;
  }
}
