package com.sporthustle.hustle.competition.repository;

import com.sporthustle.hustle.competition.entity.competition.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {}
