package com.sporthustle.hustle.common.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class BaseResponse<T> {

  protected String code;
  protected String message;
  protected T data;
}
