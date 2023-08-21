package com.sporthustle.hustle.friendmatch.repository;


import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPost;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPostType;
import com.sporthustle.hustle.sport.entity.SportEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendMatchingRepository extends JpaRepository<FriendMatchingPost, Long> {
    Page<FriendMatchingPost> findByCategoryAndSportEventOrderByStartDateAsc(FriendMatchingPostType friendMatchingPostType, SportEvent sportEventId, Pageable pageable);

}
