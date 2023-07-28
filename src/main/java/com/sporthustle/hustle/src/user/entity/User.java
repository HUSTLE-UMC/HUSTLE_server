package com.sporthustle.hustle.src.user.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.common.entity.BaseState;
import com.sporthustle.hustle.src.university.entity.University;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "User")
public class User extends BaseEntity implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", nullable = false)
  private Long id;

  @Column(name = "email", nullable = false, length = 100, unique = true)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "birth", length = 6)
  private String birth;

  @Column(name = "nickname", length = 15)
  private String nickname;

  @Column(name = "genger", length = 6)
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Column(name = "refresh_token", nullable = true)
  private String refreshToken;

  @Enumerated(EnumType.STRING)
  @Column(name = "state", nullable = false, length = 10)
  protected BaseState state = BaseState.ACTIVE;

  @Column(name = "roles")
  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> roles = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "university_id")
  private University university;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
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
  public User(
      String email,
      String password,
      String birth,
      String nickname,
      Gender gender,
      List<String> roles) {
    this.email = email;
    this.password = password;
    this.birth = birth;
    this.nickname = nickname;
    this.gender = gender;
    this.roles = roles;
  }

  public void insertRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
