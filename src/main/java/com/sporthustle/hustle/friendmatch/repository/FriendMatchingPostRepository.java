package com.sporthustle.hustle.friendmatch.repository;


import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPost;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPostType;
import com.sporthustle.hustle.sport.entity.SportEvent;
import com.sporthustle.hustle.university.entity.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendMatchingPostRepository extends JpaRepository<FriendMatchingPost, Long> {
    Page<FriendMatchingPost> findByCategoryAndSportEventOrderByStartDateAsc(FriendMatchingPostType friendMatchingPostType, SportEvent sportEventId, Pageable pageable);
    Optional<FriendMatchingPost> findById(Long id);
}
