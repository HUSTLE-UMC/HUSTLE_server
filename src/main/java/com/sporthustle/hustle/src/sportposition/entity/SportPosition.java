package com.sporthustle.hustle.src.sportposition.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.src.sportevent.entity.SportEvent;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "sportposition")
public class SportPosition extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "sportposition_id", nullable = false)
  private Long id;

  @Column(name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "sportevent_id") // 외래키
  private SportEvent sportEvent;
}
