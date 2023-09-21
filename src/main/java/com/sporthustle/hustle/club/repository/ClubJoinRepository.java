package com.sporthustle.hustle.club.repository;

import com.sporthustle.hustle.club.entity.JoinClubRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClubJoinRepository extends JpaRepository<JoinClubRequest, Long> {
    Optional<JoinClubRequest> findById(Long clubJoinRequestId);
    List<JoinClubRequest> findAllByClub_Id(Long clubId);
}
