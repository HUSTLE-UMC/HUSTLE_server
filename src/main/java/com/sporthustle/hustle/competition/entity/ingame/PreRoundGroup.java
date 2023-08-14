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
@Table(name = "PreRoundGroup")
@Entity
public class PreRoundGroup extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 20)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "competition_id", nullable = false)
  private Competition competition;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "entry_team_id", nullable = false)
  private EntryTeam entryTeam;
}
