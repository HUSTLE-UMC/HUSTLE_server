package com.sporthustle.hustle.friendmatch.repository;

import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPost;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendMatchingRequestRepository extends JpaRepository<FriendMatchingRequest, Long> {

    List<FriendMatchingRequest> findAllByFriendMatchingPost(FriendMatchingPost friendMatchingPost);
}
