package com.sporthustle.hustle.common.exception;

import com.sporthustle.hustle.common.dto.BaseErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class FileUploadExceptionHandler {

  @ExceptionHandler(SizeLimitExceededException.class)
  public ResponseEntity<BaseErrorResponse> handleSizeLimitExceededException(Exception exception) {
    ErrorCode errorCode = ErrorCode.FILE_SIZE_LIMIT;

    return ResponseEntity.status(errorCode.getStatus()).body(BaseErrorResponse.builder()
            .error(ErrorMessage.builder()
                    .code(errorCode.getCode())
                    .message(errorCode.getMessage())
                    .build())
            .build()
    );
  }
}
