package com.sporthustle.hustle.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

  @ExceptionHandler(BaseException.class)
  protected ResponseEntity<ErrorMessage> handleCustomException(BaseException e) {
    return ErrorMessage.toResponseEntity(e.getErrorCode());
  }
}
