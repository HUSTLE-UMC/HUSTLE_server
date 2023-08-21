package com.sporthustle.hustle.common.converter;

import com.sporthustle.hustle.competitions.ingame.dto.InGameCategory;
import org.springframework.core.convert.converter.Converter;

public class InGameTypeRequestConverter implements Converter<String, InGameCategory> {
  @Override
  public InGameCategory convert(String inGameType) {
    return InGameCategory.of(inGameType.toUpperCase());
  }
}
