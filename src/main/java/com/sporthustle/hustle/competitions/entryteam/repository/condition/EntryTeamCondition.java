package com.sporthustle.hustle.competitions.entryteam.repository.condition;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EntryTeamCondition {
  private Long competitionId;

  private String name;
}
