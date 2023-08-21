package com.sporthustle.hustle.friendmatch;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPost;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingRequest;
import com.sporthustle.hustle.friendmatch.repository.FriendMatchingPostRepository;
import com.sporthustle.hustle.friendmatch.repository.FriendMatchingRequestRepository;

public class FriendMatchingUtils {
    public static FriendMatchingPost getFriendMatchingPostById(
            Long friendMatchingPostId, FriendMatchingPostRepository friendMatchingPostRepository) {
        FriendMatchingPost friendMatchingPost =
                friendMatchingPostRepository
                        .findById(friendMatchingPostId)
                        .orElseThrow(() -> BaseException.from(ErrorCode.Friend_MATCHING_POST_NOT_FOUND));
        return friendMatchingPost;
    }

    public static FriendMatchingRequest getFriendMatchingRequestById(
            Long friendMatchingRequestId,
            FriendMatchingRequestRepository friendMatchingRequestRepository) {
        FriendMatchingRequest friendMatchingRequest =
                friendMatchingRequestRepository
                        .findById(friendMatchingRequestId)
                        .orElseThrow(() -> BaseException.from(ErrorCode.Friend_MATCHING_POST_NOT_FOUND));
        return friendMatchingRequest;
    }
}