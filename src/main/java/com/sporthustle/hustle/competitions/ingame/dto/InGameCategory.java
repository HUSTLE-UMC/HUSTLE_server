package com.sporthustle.hustle.competitions.ingame.dto;

public enum InGameCategory {
  PREROUND,
  FINALROUND,
  ;

  public static InGameCategory of(String inGameTypeString) {
    if (inGameTypeString == null) {
      throw new IllegalArgumentException("inGameTypeString 이 null 입니다.");
    }

    for (InGameCategory inGameCategory : InGameCategory.values()) {
      if (inGameCategory.name().equals(inGameTypeString)) {
        return inGameCategory;
      }
    }

    return InGameCategory.PREROUND;
  }
}
