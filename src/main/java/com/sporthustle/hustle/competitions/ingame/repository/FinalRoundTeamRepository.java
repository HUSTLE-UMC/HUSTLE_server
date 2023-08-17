package com.sporthustle.hustle.competitions.ingame.repository;

import com.sporthustle.hustle.competitions.ingame.entity.FinalRoundTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinalRoundTeamRepository extends JpaRepository<FinalRoundTeam, Long> {
  List<FinalRoundTeam> findAllByCompetition_Id(Long competitionId);
}
