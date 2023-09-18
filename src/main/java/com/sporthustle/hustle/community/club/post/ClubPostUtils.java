package com.sporthustle.hustle.community.club.post;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.community.club.post.entity.ClubPost;
import com.sporthustle.hustle.community.club.post.repository.ClubPostRepository;

public class ClubPostUtils {
  public static ClubPost getClubPostById(Long clubPostId, ClubPostRepository clubPostRepository) {
    return clubPostRepository
        .findById(clubPostId)
        .orElseThrow(() -> BaseException.from(ErrorCode.CLUB_POST_NOT_FOUND));
  }
}
