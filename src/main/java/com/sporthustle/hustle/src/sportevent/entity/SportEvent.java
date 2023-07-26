package com.sporthustle.hustle.src.sportevent.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "sportevent")
public class SportEvent extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "sportevent_id", nullable = false)
  private Long id;

  @Column(name = "name")
  private String name;
}
