package com.sporthustle.hustle.competitions.ingame.repository;

import com.sporthustle.hustle.competitions.ingame.entity.PreRoundDetail;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreRoundDetailRepository extends JpaRepository<PreRoundDetail, Long> {
  Optional<PreRoundDetail> findByCompetition_Id(Long competitionId);
}
