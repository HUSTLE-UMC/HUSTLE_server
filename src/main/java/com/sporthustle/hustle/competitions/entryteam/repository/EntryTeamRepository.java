package com.sporthustle.hustle.competitions.entryteam.repository;

import com.sporthustle.hustle.competitions.entryteam.entity.EntryTeam;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryTeamRepository extends JpaRepository<EntryTeam, Long> {
  List<EntryTeam> findAllByCompetition_Id(Long competitonId);

  Optional<EntryTeam> findByUser_IdAndCompetition_id(Long userId, Long competitionId);
}
