package com.sporthustle.hustle.common.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
  ALREADY_EXIST_USER(BAD_REQUEST, "ALREADY_EXIST_USER", "이미 존재하는 유저입니다."),
  USER_NOT_FOUND(BAD_REQUEST, "USER_NOT_FOUND", "해당 유저를 찾을 수 없습니다."),
  INVALID_PASSWORD(BAD_REQUEST, "INVALID_PASSWORD", "잘못된 비밀번호 입니다."),
  UNIVERSITY_NOT_FOUND(BAD_REQUEST, "UNIVERSITY_NOT_FOUND", "해당 대학을 찾을 수 없습니다."),
  REFRESH_TOKEN_NOT_FOUND(BAD_REQUEST, "REFRESH_TOKEN_NOT_FOUND", "해당 리프레쉬 토큰을 찾을 수 없습니다."),
  REFRESH_TOKEN_EXPIRED(BAD_REQUEST, "REFRESH_TOKEN_EXPIRED", "해당 리프레쉬 토큰은 만료됐습니다."),
  SPORT_EVENT_NOT_FOUND(BAD_REQUEST, "SPORT_EVENT_NOT_FOUND", "해당 종목을 찾을 수 없습니다."),
  CLUB_NOT_FOUND(BAD_REQUEST, "CLUB_NOT_FOUND", "해당 동아리를 찾을 수 없습니다."),
  MEMBER_NOT_IN_CLUB(BAD_REQUEST, "MEMBER_NOT_IN_CLUB", "해당 동아리원이 아닙니다."),
  COMPETITION_NOT_FOUND(BAD_REQUEST, "COMPETITION_NOT_FOUND", "해당 대회를 찾을 수 없습니다."),
  USER_NOT_OWNER(FORBIDDEN, "USER_NOT_OWNER", "접근 권한이 없습니다."),
  ENTRY_TEAM_NOT_FOUND(BAD_REQUEST, "ENTRY_TEAM_NOT_FOUND", "대회 참가팀이 아닙니다."),
  PRE_ROUND_DETAIL_NOT_FOUND(BAD_REQUEST, "PRE_ROUND_DETAIL_NOT_FOUND", "예선 세부 정보를 찾을 수 없습니다."),
  FINAL_ROUND_DETAIL_NOT_FOUND(BAD_REQUEST, "FINAL_ROUND_DETAIL_NOT_FOUND", "본선 세부 정보를 찾을 수 없습니다."),
  FRIEND_MATCHING_POST_NOT_FOUND(BAD_REQUEST, "FRIEND_MATCHING_POST_NOT_FOUND", "해당 교류전글을 찾을 수 없습니다.")
  ;

  private final HttpStatus status;
  private final String code;
  private final String message;

  private ErrorCode(HttpStatus status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }
}
