package com.sporthustle.hustle.competitions.entryteam.entity;

import com.sporthustle.hustle.club.entity.Club;
import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.competitions.competition.entity.Competition;
import com.sporthustle.hustle.user.entity.User;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "EntryTeam")
@Entity
public class EntryTeam extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 20)
  private String name;

  @Column(name = "phone_number", nullable = false, length = 20)
  private String phoneNumber;

  @Column(nullable = false, columnDefinition = "INT UNSIGNED default 0")
  private long score;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "competition_id", nullable = false)
  private Competition competition;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "club_id", nullable = false)
  private Club club;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Builder
  private EntryTeam(String name, String phoneNumber, Competition competition, Club club, User user) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.competition = competition;
    this.club = club;
    this.user = user;
  }

  public void updateCompetition(Competition competition) {
    this.competition = competition;
  }

  public void updateClub(Club club) {
    this.club = club;
  }

  public void updateUser(User user) {
    this.user = user;
  }
}
