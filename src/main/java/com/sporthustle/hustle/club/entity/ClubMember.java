package com.sporthustle.hustle.club.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.common.entity.BaseStatus;
import com.sporthustle.hustle.user.entity.User;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ClubMember")
@Entity
public class ClubMember extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "uniform_number", columnDefinition = "SMALLINT UNSIGNED default 1")
  private Integer uniformNumber;

  @Column(nullable = false, length = 16, columnDefinition = "CHAR(16) default 'MEMBER'")
  @Enumerated(EnumType.STRING)
  private ClubMemberRole role = ClubMemberRole.MEMBER;

  @Column(nullable = false, columnDefinition = "INT UNSIGNED default 0")
  private long point;

  @Column(nullable = false, length = 10, columnDefinition = "CHAR(10) default 'ACTIVE'")
  @Enumerated(EnumType.STRING)
  protected BaseStatus status = BaseStatus.ACTIVE;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "club_id", nullable = false)
  private Club club;

  @Builder
  private ClubMember(User user, Club club) {
    this.user = user;
    this.club = club;
  }
}
