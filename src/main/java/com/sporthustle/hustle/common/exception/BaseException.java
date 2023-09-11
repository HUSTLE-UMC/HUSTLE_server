package com.sporthustle.hustle.common.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

  private final ErrorCode errorCode;

  @Builder
  private BaseException(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public static BaseException from(ErrorCode errorCode) {
    return BaseException.builder().errorCode(errorCode).build();
  }
}
