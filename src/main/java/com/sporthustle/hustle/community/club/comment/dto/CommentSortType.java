package com.sporthustle.hustle.community.club.comment.dto;

public enum CommentSortType {
  NEW,
  LIKE_COUNT,
  ;

  public static CommentSortType of(String commentSortTypeString) {
    if (commentSortTypeString == null) {
      throw new IllegalArgumentException("commentSortTypeString 이 null 입니다.");
    }

    for (CommentSortType commentSortType : CommentSortType.values()) {
      if (commentSortType.name().equals(commentSortTypeString)) {
        return commentSortType;
      }
    }

    return CommentSortType.NEW;
  }
}
