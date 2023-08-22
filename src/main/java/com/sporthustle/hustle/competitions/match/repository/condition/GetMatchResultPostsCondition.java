package com.sporthustle.hustle.competitions.match.repository.condition;

import com.sporthustle.hustle.competitions.ingame.dto.InGameCategory;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetMatchResultPostsCondition {

  private Long competitionId;

  private InGameCategory category;

  private String groupCategory;
}
