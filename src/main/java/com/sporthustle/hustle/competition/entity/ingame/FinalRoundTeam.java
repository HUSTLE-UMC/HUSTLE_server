package com.sporthustle.hustle.competition.entity.ingame;

import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.competition.entity.competition.Competition;
import com.sporthustle.hustle.competition.entity.entryteam.EntryTeam;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "FinalRoundTeam")
@Entity
public class FinalRoundTeam extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "current_tournament_level",
      nullable = false,
      columnDefinition = "SMALLINT UNSIGNED default 1")
  private int currentTournamentLevel;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "competition_id", nullable = false)
  private Competition competition;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "entry_team_id", nullable = false)
  private EntryTeam entryTeam;
}
