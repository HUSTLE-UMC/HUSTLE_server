package com.sporthustle.hustle.club.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ClubMemberPointLog")
@Entity
public class ClubMemberPointLog extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "INT UNSIGNED default 0")
  private long point;

  @Column(columnDefinition = "TINYINT(1) default 0")
  private boolean plus = false;

  @Column(nullable = false, length = 20, columnDefinition = "CHAR(20) default 'COMPETITION'")
  @Enumerated(EnumType.STRING)
  private ClubMemberPointLogType type = ClubMemberPointLogType.COMPETITION;

  @Column(nullable = false, length = 60, columnDefinition = "VARCHAR(60) default ''")
  private String memo;
}
