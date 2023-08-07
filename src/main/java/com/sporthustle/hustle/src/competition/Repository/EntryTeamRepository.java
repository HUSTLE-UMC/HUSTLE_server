package com.sporthustle.hustle.src.competition.Repository;

import com.sporthustle.hustle.src.competition.entity.Competition;
import com.sporthustle.hustle.src.competition.entity.EntryTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntryTeamRepository extends JpaRepository<EntryTeam, Long> {

    List<EntryTeam> findByCompetition(Competition competition);

    Integer countByCompetition(Competition competition);
}
