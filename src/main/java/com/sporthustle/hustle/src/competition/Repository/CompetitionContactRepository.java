package com.sporthustle.hustle.src.competition.Repository;

import com.sporthustle.hustle.src.competition.entity.Competition;
import com.sporthustle.hustle.src.competition.entity.CompetitionContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompetitionContactRepository extends JpaRepository<CompetitionContact, Long> {
    List<CompetitionContact> findByCompetition(Competition competition);
}
