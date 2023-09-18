package com.sporthustle.hustle.competitions.match.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.competitions.competition.entity.Competition;
import com.sporthustle.hustle.competitions.entryteam.entity.EntryTeam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MatchResultPost")
@Entity
public class MatchResultPost extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 60)
  private String title;

  @Column(nullable = false, length = 10, columnDefinition = "CHAR(10) default 'PREROUND'")
  @Enumerated(EnumType.STRING)
  private MatchResultPostCategory category = MatchResultPostCategory.PREROUND;

  @Column(
      name = "group_category",
      nullable = false,
      length = 10,
      columnDefinition = "VARCHAR(20) default ''")
  private String groupCategory;

  @Column(name = "post_order", nullable = false, columnDefinition = "SMALLINT UNSIGNED default 1")
  private Integer postOrder;

  @Column(name = "match_time", nullable = false)
  private LocalDateTime matchTime;

  @Column(name = "media_url", nullable = false, length = 60)
  private String mediaUrl;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "home_entry_team_id", nullable = false)
  private EntryTeam homeEntryTeam;

  @Column(name = "home_score", nullable = false, columnDefinition = "SMALLINT UNSIGNED default 0")
  private Integer homeScore = 0;

  @Column(name = "is_home_win", columnDefinition = "TINYINT(1) default 0")
  private boolean isHomeWin = false;

  @OneToMany(mappedBy = "matchResultPost", cascade = CascadeType.PERSIST, orphanRemoval = true)
  private List<MatchResultPostScoreLog> homeMatchResultPostScoreLogs = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "away_entry_team_id", nullable = false)
  private EntryTeam awayEntryTeam;

  @Column(name = "away_score", nullable = false, columnDefinition = "SMALLINT UNSIGNED default 0")
  private Integer awayScore = 0;

  @Column(name = "is_away_win", columnDefinition = "TINYINT(1) default 0")
  private boolean isAwayWin = false;

  @OneToMany(mappedBy = "matchResultPost", cascade = CascadeType.PERSIST, orphanRemoval = true)
  private List<MatchResultPostScoreLog> awayMatchResultPostScoreLogs = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "competition_id", nullable = false)
  private Competition competition;

  @Builder
  private MatchResultPost(
      String title,
      String category,
      String groupCategory,
      Integer postOrder,
      LocalDateTime matchTime,
      String mediaUrl) {
    this.title = title;
    this.category = MatchResultPostCategory.of(category);
    this.groupCategory = groupCategory;
    this.postOrder = postOrder;
    this.matchTime = matchTime;
    this.mediaUrl = mediaUrl;
  }

  public void update(
      String title,
      String category,
      String groupCategory,
      Integer postOrder,
      LocalDateTime matchTime,
      String mediaUrl) {
    if (title != null) {
      this.title = title;
    }

    if (category != null) {
      this.category = MatchResultPostCategory.of(category);
    }

    if (groupCategory != null) {
      this.groupCategory = groupCategory;
    }

    if (postOrder != null) {
      this.postOrder = postOrder;
    }

    if (matchTime != null) {
      this.matchTime = matchTime;
    }

    if (mediaUrl != null) {
      this.mediaUrl = mediaUrl;
    }
  }

  public void updateHomeTeam(
      EntryTeam homeEntryTeam,
      Integer homeScore,
      Boolean isHomeWin,
      List<MatchResultPostScoreLog> homeMatchResultPostScoreLogs) {
    this.homeEntryTeam = homeEntryTeam;
    this.homeScore = homeScore;
    this.isHomeWin = isHomeWin;
    this.homeMatchResultPostScoreLogs = homeMatchResultPostScoreLogs;
  }

  public void updateAwayTeam(
      EntryTeam awayEntryTeam,
      Integer awayScore,
      Boolean isAwayWin,
      List<MatchResultPostScoreLog> awayMatchResultPostScoreLogs) {
    this.awayEntryTeam = awayEntryTeam;
    this.awayScore = awayScore;
    this.isAwayWin = isAwayWin;
    this.awayMatchResultPostScoreLogs = awayMatchResultPostScoreLogs;
  }

  public void updateCompetition(Competition competition) {
    this.competition = competition;
  }
}
