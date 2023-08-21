package com.sporthustle.hustle.competitions.competition.repository;

import com.sporthustle.hustle.competitions.competition.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {}
