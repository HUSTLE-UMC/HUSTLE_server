package com.sporthustle.hustle.friendmatch.repository;

import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPost;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingRequest;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendMatchingRequestRepository
    extends JpaRepository<FriendMatchingRequest, Long> {
  List<FriendMatchingRequest> findAllByFriendMatchingPost(FriendMatchingPost friendMatchingPostId);
  Optional<FriendMatchingRequest> findById(Long friendMatchingRequestid);

}
