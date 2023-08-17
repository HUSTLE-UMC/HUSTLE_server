package com.sporthustle.hustle.competitions.ingame.dto;

import com.sporthustle.hustle.competitions.ingame.entity.FinalRoundDetail;
import com.sporthustle.hustle.competitions.ingame.entity.PreRoundDetail;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DetailResponseDTO {

  private String timeTableUrl;

  public static DetailResponseDTO from(PreRoundDetail preRoundDetail) {
    return DetailResponseDTO.builder().timeTableUrl(preRoundDetail.getTimeTableUrl()).build();
  }

  public static DetailResponseDTO from(FinalRoundDetail finalRoundDetail) {
    return DetailResponseDTO.builder().timeTableUrl(finalRoundDetail.getTimeTableUrl()).build();
  }
}
