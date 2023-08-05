package com.sporthustle.hustle.src.user;

import com.sporthustle.hustle.common.entity.BaseState;
import com.sporthustle.hustle.src.user.entity.User;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmailAndState(String email, BaseState state);

  boolean existsByEmailAndState(String email, BaseState state);

  Optional<User> findByNameAndBirthAndState(String name, Date birth, BaseState state);
}
