package com.sporthustle.hustle.src.user;

import com.sporthustle.hustle.src.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String username);

  boolean existsByEmail(String email);
}
