package com.sporthustle.hustle.common.dto;

import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(builderMethodName = "builder2")
public class BasePageable<T> extends BaseResponse<List<T>> {
  protected long count;
  protected long totalPage;
  protected long totalCount;
}
