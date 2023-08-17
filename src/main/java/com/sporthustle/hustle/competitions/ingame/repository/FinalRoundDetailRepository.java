package com.sporthustle.hustle.competitions.ingame.repository;

import com.sporthustle.hustle.competitions.ingame.entity.FinalRoundDetail;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinalRoundDetailRepository extends JpaRepository<FinalRoundDetail, Long> {
  Optional<FinalRoundDetail> findByCompetition_Id(Long competitionId);
}
