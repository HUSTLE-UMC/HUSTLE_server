package com.sporthustle.hustle.src.club.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.common.entity.BaseState;
import com.sporthustle.hustle.src.sportevent.entity.SportEvent;
import com.sporthustle.hustle.src.university.entity.University;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
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
  private Long id;

  @Column(nullable = false)
  private String name;

  private String instagram;

  private String youtubeUrl;

  @Column(nullable = false)
  private String mainArea;

  private String profileImageUrl;

  @ColumnDefault("0")
  private int point;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 10)
  protected BaseState state = BaseState.ACTIVE;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "university_id", nullable = false)
  private University university;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sport_event_id", nullable = false)
  private SportEvent sportEvent;

  @Builder
  public Club(
          String name,
          String instagram,
          String youtubeUrl,
          String mainArea,
          String profileImageUrl,
          BaseState state,
          University university,
          SportEvent sportEvent) {
    this.name = name;
    this.instagram = instagram;
    this.youtubeUrl = youtubeUrl;
    this.mainArea = mainArea;
    this.profileImageUrl = profileImageUrl;
    this.state = state;
    this.university = university;
    this.sportEvent = sportEvent;
  }
}
