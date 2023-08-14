package com.sporthustle.hustle.common.dto;

import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class BasePageable<T> {
  protected long count;
  protected long totalPage;
  protected long totalCount;
  protected List<T> data;
}
