package com.sporthustle.hustle.src.sportposition.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.src.sportevent.entity.SportEvent;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SportPosition")
public class SportPosition extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "sport_position_id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "name")
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sport_event_id", nullable = false)
  private SportEvent sportEvent;

  @Builder
  public SportPosition(String name, SportEvent sportEvent) {
    this.name = name;
    this.sportEvent = sportEvent;
  }
}
