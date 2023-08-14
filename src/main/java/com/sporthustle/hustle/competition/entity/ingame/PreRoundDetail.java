package com.sporthustle.hustle.competition.entity.ingame;

import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.competition.entity.competition.Competition;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PreRoundDetail")
@Entity
public class PreRoundDetail extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "time_table_url", nullable = false)
  private String timeTableUrl;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "competition_id", nullable = false)
  private Competition competition;
}
