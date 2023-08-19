package com.sporthustle.hustle.friendmatch.repository;


import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendMatchingRepository extends JpaRepository<FriendMatchingPost, Long> {
}
