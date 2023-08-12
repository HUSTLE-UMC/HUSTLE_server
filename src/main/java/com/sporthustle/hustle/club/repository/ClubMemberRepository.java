package com.sporthustle.hustle.club.repository;

import com.sporthustle.hustle.club.entity.ClubMember;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubMemberRepository extends JpaRepository<ClubMember, Long> {

  List<ClubMember> findAllByUser_id(Long userId);

  @EntityGraph(
      attributePaths = {"user"},
      type = EntityGraph.EntityGraphType.FETCH)
  List<ClubMember> findAllByClub_id(Long clubId);

  Optional<ClubMember> findByUser_idAndClub_id(Long userId, Long clubId);
}
