package com.sporthustle.hustle.community.club.post.entity;

import com.sporthustle.hustle.club.entity.Club;
import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.common.entity.BaseStatus;
import com.sporthustle.hustle.community.club.comment.entity.ClubPostComment;
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
@Table(name = "ClubPost")
@Entity
public class ClubPost extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String title;

  @Column(columnDefinition = "TEXT")
  private String content;

  @Column(nullable = false, length = 10, columnDefinition = "CHAR(10) default 'ACTIVE'")
  @Enumerated(EnumType.STRING)
  protected BaseStatus status = BaseStatus.ACTIVE;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "club_id", nullable = false)
  private Club club;

  @OneToMany(mappedBy = "clubPost")
  private Set<ClubPostComment> comments = new HashSet<>();

  @Builder
  private ClubPost(String title, String content, User user, Club club) {
    this.title = title;
    this.content = content;
    this.user = user;
    this.club = club;
  }

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public void delete() {
    this.status = BaseStatus.INACTIVE;
  }
}
