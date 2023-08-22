package com.sporthustle.hustle.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class FileUploadExceptionHandler {

  @ExceptionHandler(SizeLimitExceededException.class)
  public ResponseEntity<ErrorMessage> handleSizeLimitExceededException(Exception ex) {
    return ErrorMessage.toResponseEntity(
        BaseException.from(ErrorCode.FILE_SIZE_LIMIT).getErrorCode());
  }
}
