package com.sporthustle.hustle.friendmatch.repository;

<<<<<<< HEAD
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPost;
=======
>>>>>>> d38ec060e9dbdd9a8171ac3230d65c16dffcca1c
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingRequest;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendMatchingRequestRepository
    extends JpaRepository<FriendMatchingRequest, Long> {
<<<<<<< HEAD
  List<FriendMatchingRequest> findAllByFriendMatchingPost(FriendMatchingPost friendMatchingPostId);

  Optional<FriendMatchingRequest> findById(Long friendMatchingRequestid);
=======
  List<FriendMatchingRequest> findAllByFriendMatchingPost(Long friendMatchingPostId);

  Optional<FriendMatchingRequest> findById(Long id);
>>>>>>> d38ec060e9dbdd9a8171ac3230d65c16dffcca1c
}
