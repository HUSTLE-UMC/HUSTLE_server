package com.sporthustle.hustle.user.entity;

import com.sporthustle.hustle.club.entity.ClubMember;
import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.common.entity.BaseStatus;
import com.sporthustle.hustle.university.entity.University;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
    name = "User",
    indexes = {
      @Index(name = "idx_name", columnList = "name"),
    })
public class User extends BaseEntity implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false, length = 128)
  private String password;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private LocalDate birthday;

  @Column(nullable = false, length = 6, columnDefinition = "CHAR(6) default 'MALE'")
  @Enumerated(EnumType.STRING)
  private Gender gender = Gender.MALE;

  @Column(name = "is_mailing", nullable = false, columnDefinition = "TINYINT(1) default 0")
  private Boolean isMailing = false;

  @Column(name = "refresh_token", nullable = false)
  private String refreshToken = "";

  @Column(
      name = "sns_type",
      nullable = false,
      length = 20,
      columnDefinition = "CHAR(20) default 'DEFAULT'")
  @Enumerated(EnumType.STRING)
  private SnsType snsType = SnsType.DEFAULT;

  @Column(
      name = "sns_id",
      nullable = false,
      length = 60,
      columnDefinition = "VARCHAR(60) default ''")
  private String snsId = "";

  @Column(nullable = false, length = 10, columnDefinition = "CHAR(10) default 'ACTIVE'")
  @Enumerated(EnumType.STRING)
  protected BaseStatus status = BaseStatus.ACTIVE;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "university_id")
  private University university;

  @Column(
      name = "role",
      nullable = false,
      length = 10,
      columnDefinition = "CHAR(10) default 'USER'")
  @Enumerated(EnumType.STRING)
  private UserRole userRole = UserRole.USER;

  @OneToMany(mappedBy = "user")
  private List<ClubMember> clubMembers = new ArrayList<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    String roleName = "ROLE_" + this.userRole.name();

    authorities.add(new SimpleGrantedAuthority(roleName));
    return authorities;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Builder
  private User(
      String email,
      String password,
      LocalDate birthday,
      String name,
      Gender gender,
      Boolean isMailing) {
    this.email = email;
    this.password = password;
    this.birthday = birthday;
    this.name = name;
    this.gender = gender;
    this.isMailing = isMailing;
  }

  public void insertRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public void updateUniversity(University university) {
    this.university = university;
  }

  public void updateSnsValue(String snsId, SnsType snsType) {
    this.snsId = snsId;
    this.snsType = snsType;
  }

  public void updateNewPassword(String newPasswordHashed) {
    this.password = newPasswordHashed;
  }

  public void updateMyProfilePartial(String name, LocalDate birthday, String gender) {
    if (name != null) {
      this.name = name;
    }

    if (birthday != null) {
      this.birthday = birthday;
    }

    if (gender != null) {
      this.gender = Gender.valueOf(gender);
    }
  }

  public void delete() {
    this.status = BaseStatus.INACTIVE;
  }
}
