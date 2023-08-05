package com.sporthustle.hustle.src.club.model;

import com.sporthustle.hustle.src.club.entity.Club;
import com.sporthustle.hustle.src.competition.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long> {
    List<Club> findByCompetitionId(Long competitionId);
    Club findByName(String name);
}
