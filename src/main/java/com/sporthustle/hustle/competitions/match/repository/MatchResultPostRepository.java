package com.sporthustle.hustle.competitions.match.repository;

import com.sporthustle.hustle.competitions.match.entity.MatchResultPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchResultPostRepository extends JpaRepository<MatchResultPost, Long> {

  List<MatchResultPost> findAllByCompetition_IdAndGroupCategory(Long competitionId, String groupCategory);
}
