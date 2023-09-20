package com.sporthustle.hustle.common.converter;

import com.sporthustle.hustle.community.club.comment.dto.CommentSortType;
import org.springframework.core.convert.converter.Converter;

public class CommentSortTypeRequestConverter implements Converter<String, CommentSortType> {
  @Override
  public CommentSortType convert(String commentSortTypeString) {
    return CommentSortType.of(commentSortTypeString.toUpperCase());
  }
}
