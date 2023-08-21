package com.sporthustle.hustle.friendmatch.entity;

import com.sporthustle.hustle.club.entity.Club;
import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.user.entity.User;
import javax.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "FriendMatchingRequest")
@Entity
@Setter
public class FriendMatchingRequest extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 10, columnDefinition = "CHAR(10) default 'WAIT'")
  @Enumerated(EnumType.STRING)
  private FriendMatchingRequestType type = FriendMatchingRequestType.WAIT;

  @Column(columnDefinition = "GEOMETRY")
  private Point location;

  @Column(
      name = "location_address",
      nullable = false,
      length = 60,
      columnDefinition = "VARCHAR(60) default ''")
  private String locationAddress;

  @Column(nullable = false, length = 20)
  private String name;

  @Column(name = "phone_number", nullable = false, length = 20)
  private String phoneNumber;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "friend_matching_post_id", nullable = false)
  private FriendMatchingPost friendMatchingPost;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "club_id", nullable = false)
  private Club club;

  @Builder
  FriendMatchingRequest(
      Point location,
      String locationAddress,
      String name,
      String phoneNumber,
      FriendMatchingRequestType type) {
    this.location = location;
    this.locationAddress = locationAddress;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.type = type;
  }

  public void updateType(FriendMatchingRequestType type) {
    this.type = type;
  }
}
