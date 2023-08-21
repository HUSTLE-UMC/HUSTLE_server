package com.sporthustle.hustle.competitions.ingame.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.competitions.competition.entity.Competition;
import com.sporthustle.hustle.competitions.entryteam.entity.EntryTeam;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PreRoundGroup")
@Entity
public class PreRoundGroup extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "group_category", nullable = false, length = 20)
  private String groupCategory;

  @Column(name = "match_count", nullable = false, columnDefinition = "SMALLINT UNSIGNED default 0")
  private int matchCount;

  @Column(name = "win_count", nullable = false, columnDefinition = "SMALLINT UNSIGNED default 0")
  private int winCount;

  @Column(name = "lose_count", nullable = false, columnDefinition = "SMALLINT UNSIGNED default 0")
  private int loseCount;

  @Column(name = "draw_count", nullable = false, columnDefinition = "SMALLINT UNSIGNED default 0")
  private int drawCount;

  @Column(
      name = "score_difference_count",
      nullable = false,
      columnDefinition = "SMALLINT UNSIGNED default 0")
  private int scoreDifferenceCount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "competition_id", nullable = false)
  private Competition competition;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "entry_team_id", nullable = false)
  private EntryTeam entryTeam;

  @Builder
  private PreRoundGroup(String groupCategory) {
    this.groupCategory = groupCategory;
  }

  public void updateCompetition(Competition competition) {
    this.competition = competition;
  }

  public void updateEntryTeam(EntryTeam entryTeam) {
    this.entryTeam = entryTeam;
  }
}
