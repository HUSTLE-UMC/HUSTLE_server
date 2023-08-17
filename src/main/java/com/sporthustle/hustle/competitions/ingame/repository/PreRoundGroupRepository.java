package com.sporthustle.hustle.competitions.ingame.repository;

import com.sporthustle.hustle.competitions.ingame.entity.PreRoundGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreRoundGroupRepository extends JpaRepository<PreRoundGroup, Long> {
  List<PreRoundGroup> findAllByCompetition_Id(Long competitionId);
}
