package com.sporthustle.hustle.src.user.entity;

import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.common.entity.BaseState;
import com.sporthustle.hustle.src.university.entity.University;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
@Table(
    name = "User",
    indexes = {@Index(name = "i_find_email_and_state", columnList = "email, state")})
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
  private Date birth;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Gender gender;

  private String refreshToken;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  protected BaseState state = BaseState.ACTIVE;

  @Column(nullable = false)
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
      String email, String password, Date birth, String name, Gender gender, List<String> roles) {
    this.email = email;
    this.password = password;
    this.birth = birth;
    this.name = name;
    this.gender = gender;
    this.roles = roles;
  }

  public void insertRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public void changePassword(String password) {
    this.password = password;
  }
}
