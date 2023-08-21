package com.sporthustle.hustle.friendmatch.entity;

import com.sporthustle.hustle.club.entity.Club;
import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.common.entity.BaseStatus;
import com.sporthustle.hustle.sport.entity.SportEvent;
import com.sporthustle.hustle.user.entity.User;
import org.locationtech.jts.geom.Point;
import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "FriendMatchingPost")
@Entity
public class FriendMatchingPost extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String title;

  @Column(nullable = false, length = 20, columnDefinition = "CHAR(20) default 'INVITE'")
  @Enumerated(EnumType.STRING)
  private FriendMatchingPostType category = FriendMatchingPostType.INVITE;

  @Column(nullable = false, length = 20)
  private String name;

  @Column(name = "phone_number", nullable = false, length = 20)
  private String phoneNumber;

  //일시
  @Column(name = "start_date", nullable = false)
  private LocalDateTime startDate;

  @Column(nullable = false, columnDefinition = "GEOMETRY")
  private Point location;

  @Column(
          name = "location_address",
          nullable = false,
          length = 60,
          columnDefinition = "VARCHAR(60) default ''")
  private String locationAddress;

  @Column(nullable = false, length = 10, columnDefinition = "CHAR(10) default 'ACTIVE'")
  @Enumerated(EnumType.STRING)
  protected BaseStatus status = BaseStatus.ACTIVE;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "club_id", nullable = false)
  private Club club;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sport_event_id", nullable = false)
  private SportEvent sportEvent;

  //// 주요활동지역 필요

  @Builder
  public FriendMatchingPost(
          String title,
          FriendMatchingPostType category,
          String name,
          String phoneNumber,
          Point location,
          LocalDateTime startDate,
          String locationAddress) {
    this.title = title;
    this.category = category;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.location = location;
    this.startDate = startDate;
    this.locationAddress = locationAddress;
  }

}
