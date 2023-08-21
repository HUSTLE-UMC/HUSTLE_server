package com.sporthustle.hustle.friendmatch.repository;


import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPost;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPostType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendMatchingRepository extends JpaRepository<FriendMatchingPost, Long> {
    Page<FriendMatchingPost> findByCategoryOrderByCategoryAsc(FriendMatchingPostType friendMatchingPostType, Pageable pageable);

}
