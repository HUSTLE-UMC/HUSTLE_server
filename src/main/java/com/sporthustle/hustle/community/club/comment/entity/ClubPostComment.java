package com.sporthustle.hustle.community.club.comment.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.common.entity.BaseStatus;
import com.sporthustle.hustle.community.club.post.entity.ClubPost;
import com.sporthustle.hustle.user.entity.User;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ClubPostComment")
@Entity
public class ClubPostComment extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "TEXT")
  private String content;

  @Column(name = "like_count", nullable = false, columnDefinition = "INT UNSIGNED default 0")
  private long likeCount;

  @Column(nullable = false, length = 10, columnDefinition = "CHAR(10) default 'ACTIVE'")
  @Enumerated(EnumType.STRING)
  protected BaseStatus status = BaseStatus.ACTIVE;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "club_post_id", nullable = false)
  private ClubPost clubPost;

  @OneToMany(mappedBy = "clubPostComment", cascade = CascadeType.PERSIST, orphanRemoval = true)
  private Set<ClubPostCommentLike> clubPostCommentLikeSet = new HashSet<>();

  @Builder
  private ClubPostComment(String content, User user, ClubPost clubPost) {
    this.content = content;
    this.user = user;
    this.clubPost = clubPost;
  }

  public void update(String content) {
    this.content = content;
  }

  public void delete() {
    this.status = BaseStatus.INACTIVE;
  }

  public boolean hasUserInCommentLikeSet(User viewer) {
    return clubPostCommentLikeSet.stream()
        .anyMatch(clubPostCommentLike -> clubPostCommentLike.getUser().getId() == viewer.getId());
  }

  public void increaseLikeCount() {
    if (this.likeCount < Long.MAX_VALUE) {
      this.likeCount++;
    }
  }

  public void decreaseLikeCount() {
    if (this.likeCount > 0) {
      this.likeCount--;
    }
  }
}
