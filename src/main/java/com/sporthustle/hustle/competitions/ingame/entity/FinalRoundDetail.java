package com.sporthustle.hustle.competitions.ingame.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.competitions.competition.entity.Competition;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "FinalRoundDetail")
@Entity
public class FinalRoundDetail extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "time_table_url", nullable = false)
  private String timeTableUrl;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "competition_id", nullable = false)
  private Competition competition;

  @Builder
  private FinalRoundDetail(String timeTableUrl) {
    this.timeTableUrl = timeTableUrl;
  }

  public void updateCompetition(Competition competition) {
    this.competition = competition;
  }

  public void updateTimeTableUrl(String timeTableUrl) {
    this.timeTableUrl = timeTableUrl;
  }
}
