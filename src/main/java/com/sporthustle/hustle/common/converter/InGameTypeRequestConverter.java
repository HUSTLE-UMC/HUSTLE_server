package com.sporthustle.hustle.common.converter;

import com.sporthustle.hustle.competitions.ingame.dto.InGameType;
import org.springframework.core.convert.converter.Converter;

public class InGameTypeRequestConverter implements Converter<String, InGameType> {
  @Override
  public InGameType convert(String inGameType) {
    return InGameType.of(inGameType.toUpperCase());
  }
}
