package com.sporthustle.hustle.src.sportevent.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.src.sportposition.entity.SportPosition;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SportEvent")
public class SportEvent extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String name;

  @OneToMany(mappedBy = "sportEvent")
  private List<SportPosition> sportPositions = new ArrayList<>();
}
