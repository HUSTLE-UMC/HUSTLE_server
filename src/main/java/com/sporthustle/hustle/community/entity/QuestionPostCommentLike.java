package com.sporthustle.hustle.community.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.user.entity.User;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "QuestionPostCommentLike")
@Entity
public class QuestionPostCommentLike extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_post_comment_id", nullable = false)
  private QuestionPostComment questionPostComment;
}
