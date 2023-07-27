package com.sporthustle.hustle.src.club.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.common.entity.BaseState;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Club")
public class Club extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "club_id", nullable = false)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "instagramAddress")
  private String instagramAddress;

  @Column(name = "youtubeUrl", length = 2100)
  private String youtubeUrl;

  @Column(name = "mainArea")
  private String mainArea;

  @Column(name = "profileImageUrl", length = 2100)
  private String profileImageUrl;

  @Column(name = "point")
  @ColumnDefault("0")
  private int point;

  @Enumerated(EnumType.STRING)
  @Column(name = "state", nullable = false, length = 10)
  protected BaseState state = BaseState.ACTIVE;
}
