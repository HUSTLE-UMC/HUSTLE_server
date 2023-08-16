package com.sporthustle.hustle.competitions.competition.repository.condition;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompetitionsBeforeCompleteCondition {
  private Long sportEventId;
}
