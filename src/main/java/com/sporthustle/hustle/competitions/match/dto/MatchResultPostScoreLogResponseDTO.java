package com.sporthustle.hustle.competitions.match.dto;

import com.sporthustle.hustle.competitions.match.entity.MatchResultPostScoreLog;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MatchResultPostScoreLogResponseDTO {

  private Long id;

  private String name;

  private Long score;

  private Map<String, Object> extra;

  private Long userId;

  public static MatchResultPostScoreLogResponseDTO from(
      MatchResultPostScoreLog matchResultPostScoreLog) {
    return MatchResultPostScoreLogResponseDTO.builder()
        .id(matchResultPostScoreLog.getId())
        .name(matchResultPostScoreLog.getName())
        .score(matchResultPostScoreLog.getScore())
        .extra(matchResultPostScoreLog.getExtra())
        .userId(matchResultPostScoreLog.getId())
        .build();
  }
}
