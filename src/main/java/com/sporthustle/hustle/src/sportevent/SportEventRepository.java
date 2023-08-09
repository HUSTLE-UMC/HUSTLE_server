package com.sporthustle.hustle.src.sportevent;

import com.sporthustle.hustle.src.sportevent.entity.SportEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportEventRepository extends JpaRepository<SportEvent, Long> {
}
