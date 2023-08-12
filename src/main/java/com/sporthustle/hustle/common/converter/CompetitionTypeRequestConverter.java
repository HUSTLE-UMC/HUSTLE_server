package com.sporthustle.hustle.common.converter;

import com.sporthustle.hustle.competition.dto.CompetitionStateRequest;
import org.springframework.core.convert.converter.Converter;

public class CompetitionTypeRequestConverter implements Converter<String, CompetitionStateRequest> {
  @Override
  public CompetitionStateRequest convert(String competitionState) {
    return CompetitionStateRequest.of(competitionState);
  }
}
