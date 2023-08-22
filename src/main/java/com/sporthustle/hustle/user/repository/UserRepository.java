package com.sporthustle.hustle.user.repository;

import com.sporthustle.hustle.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);

  Optional<User> findByName(String name);

  Optional<User> findByRefreshToken(String refreshToken);

  boolean existsByEmailAndSnsId(String email, String snsId);
}
