package com.sporthustle.hustle.competitions.ingame.dto;

public enum InGameType {
  PREROUND,
  FINALROUND,
  ;

  public static InGameType of(String inGameTypeString) {
    if (inGameTypeString == null) {
      throw new IllegalArgumentException("inGameTypeString 이 null 입니다.");
    }

    for (InGameType inGameType : InGameType.values()) {
      if (inGameType.name().equals(inGameTypeString)) {
        return inGameType;
      }
    }

    return InGameType.PREROUND;
  }
}
