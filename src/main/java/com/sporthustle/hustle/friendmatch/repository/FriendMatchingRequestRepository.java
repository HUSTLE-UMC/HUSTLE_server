package com.sporthustle.hustle.friendmatch.repository;

import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPost;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendMatchingRequestRepository extends JpaRepository<FriendMatchingRequest, Long> {
    List<FriendMatchingRequest> findAllByFriendMatchingPost(Long friendMatchingPostId);
    Optional<FriendMatchingRequest> findById(Long id);
}