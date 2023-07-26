package com.sporthustle.hustle.src.university.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.src.user.entity.User;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "University")
public class University extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "university_id", nullable = false)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "addess")
  private String address;

  @OneToOne(mappedBy = "university")
  private User user;
}
