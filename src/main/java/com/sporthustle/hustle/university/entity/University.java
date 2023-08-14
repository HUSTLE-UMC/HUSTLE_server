package com.sporthustle.hustle.university.entity;

import com.sporthustle.hustle.club.entity.Club;
import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.user.entity.User;
import java.util.List;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "University")
public class University extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 45, unique = true)
  private String name;

  @Column(nullable = false, length = 60)
  private String address;

  @OneToMany(mappedBy = "university")
  private List<User> users;

  @OneToMany(mappedBy = "university")
  private List<Club> clubs;
}
