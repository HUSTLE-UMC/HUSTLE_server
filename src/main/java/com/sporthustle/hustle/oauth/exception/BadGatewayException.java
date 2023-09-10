package com.sporthustle.hustle.oauth.exception;

import com.sporthustle.hustle.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class BadGatewayException extends RuntimeException {

  public BadGatewayException() {
    super(ErrorCode.UNKNOWN_ERROR.getMessage());
  }
}
