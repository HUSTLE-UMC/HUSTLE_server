package com.sporthustle.hustle.common.exception;

import com.sporthustle.hustle.common.dto.BaseErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

  @ExceptionHandler(BaseException.class)
  protected ResponseEntity<BaseErrorResponse> handleBaseException(BaseException baseException) {
    ErrorCode errorCode = baseException.getErrorCode();

    return ResponseEntity.status(errorCode.getStatus())
        .body(
            BaseErrorResponse.builder()
                .error(
                    ErrorMessage.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build())
                .build());
  }
}
