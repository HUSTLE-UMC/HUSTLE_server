package com.sporthustle.hustle.competitions.match.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.competitions.competition.entity.Competition;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MatchResultPost")
@Entity
public class MatchResultPost extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 60)
  private String title;

  @Column(nullable = false, length = 10, columnDefinition = "CHAR(10) default 'PREROUND'")
  @Enumerated(EnumType.STRING)
  private MatchResultPostCategory category = MatchResultPostCategory.PREROUND;

  @Column(
      name = "group_category",
      nullable = false,
      length = 10,
      columnDefinition = "CHAR(10) default ''")
  private String groupCategory;

  @Column(name = "post_order", nullable = false, columnDefinition = "SMALLINT UNSIGNED default 1")
  private Integer postOrder;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "competition_id", nullable = false)
  private Competition competition;
}
