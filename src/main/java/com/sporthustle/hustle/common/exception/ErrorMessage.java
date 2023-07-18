package com.sporthustle.hustle.common.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ErrorMessage {

  private HttpStatus httpStatus;
  private String errorCode;
  private String errorMessage;

  public static ResponseEntity<ErrorMessage> toResponseEntity(ErrorCode e) {
    return ResponseEntity.status(e.getStatus())
        .body(
            ErrorMessage.builder()
                .httpStatus(e.getStatus())
                .errorCode(e.getCode())
                .errorMessage(e.getMessage())
                .build());
  }
}
