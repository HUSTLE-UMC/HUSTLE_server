package com.sporthustle.hustle.competitions.match.repository;

import com.sporthustle.hustle.competitions.match.entity.MatchResultPostScoreLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchResultPostScoreLogRepository
    extends JpaRepository<MatchResultPostScoreLog, Long> {
  void deleteAllByMatchResultPost_Id(Long matchResultPostId);
}
