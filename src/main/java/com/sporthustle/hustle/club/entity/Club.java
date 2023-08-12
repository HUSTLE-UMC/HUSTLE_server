package com.sporthustle.hustle.club.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.common.entity.BaseStatus;
import com.sporthustle.hustle.sport.entity.SportEvent;
import com.sporthustle.hustle.university.entity.University;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Club")
@Entity
public class Club extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 60)
  private String name;

  @Column(length = 40)
  private String instagram;

  @Column(name = "youtube_url")
  private String youtubeUrl;

  @Column(name = "main_area", nullable = false, length = 45)
  private String mainArea;

  @Column(name = "profile_image_url")
  private String profileImageUrl;

  @Column(nullable = false, columnDefinition = "INT UNSIGNED default 0")
  private long point;

  @Column(nullable = false, length = 10, columnDefinition = "CHAR(10) default 'ACTIVE'")
  @Enumerated(EnumType.STRING)
  protected BaseStatus status = BaseStatus.ACTIVE;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "university_id", nullable = false)
  private University university;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sport_event_id", nullable = false)
  private SportEvent sportEvent;

  @OneToMany(mappedBy = "club")
  private List<ClubMember> clubMembers = new ArrayList<>();

  @Builder
  private Club(
      String name, String instagram, String youtubeUrl, String mainArea, String profileImageUrl) {
    this.name = name;
    this.instagram = instagram;
    this.youtubeUrl = youtubeUrl;
    this.mainArea = mainArea;
    this.profileImageUrl = profileImageUrl;
  }

  public void updateUniversity(University university) {
    this.university = university;
  }

  public void updateSportEvent(SportEvent sportEvent) {
    this.sportEvent = sportEvent;
  }

  public void update(
      String name, String instagram, String youtubeUrl, String mainArea, String profileImageUrl) {
    if (name != null) {
      this.name = name;
    }

    if (instagram != null) {
      this.instagram = instagram;
    }

    if (youtubeUrl != null) {
      this.youtubeUrl = youtubeUrl;
    }

    if (mainArea != null) {
      this.mainArea = mainArea;
    }

    if (profileImageUrl != null) {
      this.profileImageUrl = profileImageUrl;
    }
  }

  public void delete() {
    this.status = BaseStatus.INACTIVE;
  }
}
