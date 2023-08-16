package com.sporthustle.hustle.competition.repository;

import com.sporthustle.hustle.competition.entity.entryteam.EntryTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntryTeamRepository extends JpaRepository<EntryTeam, Long> {
  List<EntryTeam> findAllByCompetition_Id(Long competitonId);

  Optional<EntryTeam> findByUser_IdAndCompetition_id(Long userId, Long competitionId);
}
