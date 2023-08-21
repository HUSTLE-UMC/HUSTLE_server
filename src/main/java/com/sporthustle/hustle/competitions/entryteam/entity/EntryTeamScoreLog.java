package com.sporthustle.hustle.competitions.entryteam.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "EntryTeamScoreLog")
@Entity
public class EntryTeamScoreLog extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, columnDefinition = "INT UNSIGNED default 0")
  private long score;

  @Column(columnDefinition = "TINYINT(1) default 1")
  private boolean plus = false;

  @Column(nullable = false, length = 20, columnDefinition = "CHAR(20) default 'MATCH'")
  @Enumerated(EnumType.STRING)
  private EntryTeamScoreLogType type = EntryTeamScoreLogType.MATCH;

  @Column(nullable = false, length = 60, columnDefinition = "VARCHAR(60) default ''")
  private String memo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "entry_team_id", nullable = false)
  private EntryTeam entryTeam;
}
