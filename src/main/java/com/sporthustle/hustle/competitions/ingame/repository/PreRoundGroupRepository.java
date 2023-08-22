package com.sporthustle.hustle.competitions.ingame.repository;

import com.sporthustle.hustle.competitions.ingame.entity.PreRoundGroup;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreRoundGroupRepository extends JpaRepository<PreRoundGroup, Long> {
  List<PreRoundGroup> findAllByCompetition_Id(Long competitionId);

  List<PreRoundGroup> findAllByCompetition_IdAndGroupCategory(
      Long competitionId, String groupCategory);

  Optional<PreRoundGroup> findByCompetition_IdAndEntryTeam_Id(Long competitionId, Long entryTeamId);
}
