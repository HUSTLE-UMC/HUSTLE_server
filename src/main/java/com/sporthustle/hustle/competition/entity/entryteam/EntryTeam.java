package com.sporthustle.hustle.competition.entity.entryteam;

import com.sporthustle.hustle.club.entity.Club;
import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.competition.entity.competition.Competition;
import com.sporthustle.hustle.user.entity.User;
import javax.persistence.*;
import lombok.AccessLevel;
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
}
