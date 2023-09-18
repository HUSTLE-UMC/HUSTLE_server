package com.sporthustle.hustle.competitions.competition.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.common.entity.BaseStatus;
import com.sporthustle.hustle.sport.entity.SportEvent;
import com.sporthustle.hustle.user.entity.User;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Competition")
@Entity
public class Competition extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 60)
  private String title;

  @Column(nullable = false, length = 45)
  private String host;

  @Column(nullable = false, length = 60)
  private String place;

  @Column(name = "start_date", nullable = false)
  private LocalDateTime startDate;

  @Column(name = "end_date", nullable = false)
  private LocalDateTime endDate;

  @Column(name = "recruitment_start_date", nullable = false)
  private LocalDateTime recruitmentStartDate;

  @Column(name = "recruitment_end_date", nullable = false)
  private LocalDateTime recruitmentEndDate;

  @Column(name = "entry_fee", nullable = false, columnDefinition = "INT UNSIGNED default 0")
  private long entryFee;

  @Column(
      name = "max_entry_count",
      nullable = false,
      columnDefinition = "SMALLINT UNSIGNED default 1")
  private Integer maxEntryCount;

  @Column(name = "entry_count", nullable = false, columnDefinition = "SMALLINT UNSIGNED default 0")
  private Integer entryCount = 0;

  @Column(nullable = false, length = 60, columnDefinition = "VARCHAR(60) default ''")
  private String sponsor;

  @Column(name = "poster_url", nullable = false)
  private String posterUrl;

  @Column(
      name = "pre_round_group_count",
      nullable = false,
      columnDefinition = "SMALLINT UNSIGNED default 1")
  private Integer preRoundGroupCount;

  @Column(
      name = "final_round_team_count",
      nullable = false,
      columnDefinition = "SMALLINT UNSIGNED default 1")
  private Integer finalRoundTeamCount;

  @Column(nullable = false, length = 10, columnDefinition = "CHAR(10) default 'ACTIVE'")
  @Enumerated(EnumType.STRING)
  private BaseStatus status = BaseStatus.ACTIVE;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sport_event_id", nullable = false)
  private SportEvent sportEvent;

  @OneToMany(mappedBy = "competition", cascade = CascadeType.PERSIST, orphanRemoval = true)
  private Set<CompetitionContact> contacts = new HashSet<>();

  @Builder
  private Competition(
      String title,
      String host,
      String place,
      LocalDateTime startDate,
      LocalDateTime endDate,
      LocalDateTime recruitmentStartDate,
      LocalDateTime recruitmentEndDate,
      Long entryFee,
      Integer maxEntryCount,
      String sponsor,
      String posterUrl,
      Integer preRoundGroupCount,
      Integer finalRoundTeamCount) {
    this.title = title;
    this.host = host;
    this.place = place;
    this.startDate = startDate;
    this.endDate = endDate;
    this.recruitmentStartDate = recruitmentStartDate;
    this.recruitmentEndDate = recruitmentEndDate;
    this.entryFee = entryFee;
    this.maxEntryCount = maxEntryCount;
    this.sponsor = sponsor;
    this.posterUrl = posterUrl;
    this.preRoundGroupCount = preRoundGroupCount;
    this.finalRoundTeamCount = finalRoundTeamCount;
  }

  public void updateUser(User user) {
    this.user = user;
  }

  public void updateSportEvent(SportEvent sportEvent) {
    this.sportEvent = sportEvent;
  }

  public void updateEntryCount(int entryCount) {
    if (entryCount >= 0) {
      this.entryCount = entryCount;
    }
  }

  public void updateContacts(Set<CompetitionContact> contacts) {
    this.contacts.clear();
    this.contacts = contacts;
  }

  public void update(
      String title,
      String host,
      String place,
      LocalDateTime startDate,
      LocalDateTime endDate,
      LocalDateTime recruitmentStartDate,
      LocalDateTime recruitmentEndDate,
      Long entryFee,
      Integer maxEntryCount,
      String sponsor,
      String posterUrl,
      Integer preRoundGroupCount,
      Integer finalRoundTeamCount) {
    if (title != null) {
      this.title = title;
    }

    if (host != null) {
      this.host = host;
    }

    if (place != null) {
      this.place = place;
    }

    if (startDate != null) {
      this.startDate = startDate;
    }

    if (endDate != null) {
      this.endDate = endDate;
    }

    if (recruitmentStartDate != null) {
      this.recruitmentStartDate = recruitmentStartDate;
    }

    if (recruitmentEndDate != null) {
      this.recruitmentEndDate = recruitmentEndDate;
    }

    if (entryFee != null && entryFee >= 0) {
      this.entryFee = entryFee;
    }

    if (maxEntryCount != null & maxEntryCount > 0) {
      this.maxEntryCount = maxEntryCount;
    }

    if (sponsor != null) {
      this.sponsor = sponsor;
    }

    if (posterUrl != null) {
      this.posterUrl = posterUrl;
    }

    if (preRoundGroupCount != null && preRoundGroupCount > 0) {
      this.preRoundGroupCount = preRoundGroupCount;
    }

    if (finalRoundTeamCount != null && finalRoundTeamCount > 0) {
      this.finalRoundTeamCount = finalRoundTeamCount;
    }
  }

  public void delete() {
    this.status = BaseStatus.INACTIVE;
  }
}
