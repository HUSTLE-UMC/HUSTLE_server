package com.sporthustle.hustle.community.club.post.repository;

import com.sporthustle.hustle.community.club.post.entity.ClubPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubPostRepository extends JpaRepository<ClubPost, Long> {
  Page<ClubPost> findAllByClub_IdOrderByIdDesc(Long clubId, Pageable pageable);
}
