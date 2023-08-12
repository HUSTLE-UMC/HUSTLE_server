package com.sporthustle.hustle.sport;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.sport.entity.SportEvent;
import com.sporthustle.hustle.sport.repository.SportEventRepository;

public class SportUtils {

  public static SportEvent getSportEventById(
      Long sportEventId, SportEventRepository sportEventRepository) {
    SportEvent sportEvent =
        sportEventRepository
            .findById(sportEventId)
            .orElseThrow(() -> BaseException.from(ErrorCode.SPORT_EVENT_NOT_FOUND));
    return sportEvent;
  }
}
