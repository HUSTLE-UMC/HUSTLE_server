package com.sporthustle.hustle.club.repository;

import com.sporthustle.hustle.club.entity.Club;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
  @EntityGraph(
      attributePaths = {"university", "sportEvent"},
      type = EntityGraph.EntityGraphType.FETCH)
  List<Club> findAllByUniversity_idAndNameStartsWith(Long universityId, String name);

  List<Club> findAllByIdIn(List<Long> idList);

  List<Club> findAllByUniversity_id(Long universityId);
}
