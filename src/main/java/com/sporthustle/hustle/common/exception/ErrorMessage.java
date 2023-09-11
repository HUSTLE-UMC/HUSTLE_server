package com.sporthustle.hustle.common.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorMessage {

  private String code;
  private String message;
}
