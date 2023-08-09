package com.sporthustle.hustle.common.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
  ALREADY_EXIST_USER(BAD_REQUEST, "ALREADY_EXIST_USER", "이미 존재하는 유저입니다."),
  USER_NOT_FOUND(NOT_FOUND, "USER_NOT_FOUND", "해당 유저를 찾을 수 없습니다."),
  INVALID_PASSWORD(BAD_REQUEST, "INVALID_PASSWORD", "잘못된 비밀번호 입니다."),
    UNIVERSITY_NOT_FOUND(NOT_FOUND,"UNIVERSITY_NOT_FOUND", "해당 대학교 정보를 찾을 수 없습니다."),
  SPORT_EVENT_NOT_FOUND(NOT_FOUND,"SPORT_EVENT_NOT_FOUND" ,"해당 sportevent를 찾을 수 없습니다." );

  private final HttpStatus status;
  private final String code;
  private final String message;

  private ErrorCode(HttpStatus status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }
}
