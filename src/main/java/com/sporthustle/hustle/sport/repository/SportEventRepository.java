package com.sporthustle.hustle.sport.repository;

import com.sporthustle.hustle.sport.entity.SportEvent;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportEventRepository extends JpaRepository<SportEvent, Long> {

  Optional<SportEvent> findById(Long id);
}
