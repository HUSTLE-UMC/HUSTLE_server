package com.sporthustle.hustle.common.dto;

import com.sporthustle.hustle.common.exception.ErrorMessage;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class BaseErrorResponse {

  private ErrorMessage error;
}
