package com.sporthustle.hustle.friendmatch;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPost;
import com.sporthustle.hustle.friendmatch.repository.FriendMatchingPostRepository;

public class FriendMatchingUtils {
    public static FriendMatchingPost getFriendMatchingPostById(
            Long friendMatchingPostId, FriendMatchingPostRepository friendMatchingPostRepository) {
        FriendMatchingPost friendMatchingPost =
                friendMatchingPostRepository
                        .findById(friendMatchingPostId)
                        .orElseThrow(() -> BaseException.from(ErrorCode.Friend_MATCHING_POST_NOT_FOUND));
        return friendMatchingPost;
    }

}
