package com.sporthustle.hustle.competition.entity.match;

import com.sporthustle.hustle.club.entity.Club;
import com.sporthustle.hustle.common.entity.BaseEntity;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MatchResultPostClub")
@Entity
public class MatchResultPostClub extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "INT UNSIGNED default 0")
  private Long score;

  @Column(name = "is_win", columnDefinition = "TINYINT(1) default 0")
  private boolean isWin = false;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "match_result_post_id", nullable = false)
  private MatchResultPost matchResultPost;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "club_id", nullable = false)
  private Club club;
}
