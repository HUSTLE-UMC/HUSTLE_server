package com.sporthustle.hustle.competitions.match.entity;

import com.sporthustle.hustle.competitions.ingame.dto.InGameCategory;

public enum MatchResultPostCategory {
  PREROUND,
  FINAL,
  ;

  public static MatchResultPostCategory of(String matchResultPostCategoryString) {
    if (matchResultPostCategoryString == null) {
      throw new IllegalArgumentException("matchResultPostCategory 이 null 입니다.");
    }

    for (MatchResultPostCategory matchResultPostCategory : MatchResultPostCategory.values()) {
      if (matchResultPostCategory.name().equals(matchResultPostCategoryString)) {
        return matchResultPostCategory;
      }
    }

    return MatchResultPostCategory.PREROUND;
  }

  public static MatchResultPostCategory from(InGameCategory inGameCategory) {
    if (inGameCategory == null) {
      throw new IllegalArgumentException("inGameCategory 이 null 입니다.");
    }

    for (MatchResultPostCategory matchResultPostCategory : MatchResultPostCategory.values()) {
      if (matchResultPostCategory.name().equals(inGameCategory.name())) {
        return matchResultPostCategory;
      }
    }

    return MatchResultPostCategory.PREROUND;
  }
}
