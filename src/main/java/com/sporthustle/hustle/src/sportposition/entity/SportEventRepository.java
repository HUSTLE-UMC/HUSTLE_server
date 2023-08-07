package com.sporthustle.hustle.src.sportposition.entity;

import com.sporthustle.hustle.src.sportevent.entity.SportEvent;
import com.sporthustle.hustle.src.university.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportEventRepository extends JpaRepository<SportEvent, Long> {
    SportEvent findByName(String event);
}
