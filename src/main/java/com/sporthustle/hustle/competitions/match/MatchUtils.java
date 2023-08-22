package com.sporthustle.hustle.competitions.match;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.competitions.match.entity.MatchResultPost;
import com.sporthustle.hustle.competitions.match.repository.MatchResultPostRepository;

public class MatchUtils {
  public static MatchResultPost getMatchResultPostById(
      Long id, MatchResultPostRepository matchResultPostRepository) {
    MatchResultPost matchResultPost =
        matchResultPostRepository
            .findById(id)
            .orElseThrow(() -> BaseException.from(ErrorCode.MATCH_RESULT_POST_NOT_FOUND));
    return matchResultPost;
  }
}
