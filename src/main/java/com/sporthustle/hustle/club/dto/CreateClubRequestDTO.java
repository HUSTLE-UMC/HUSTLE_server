package com.sporthustle.hustle.club.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateClubRequestDTO {

  @NotBlank(message = "동아리명은 필수 입력 값입니다.")
  private String name;

  private String instagram;

  private String youtubeUrl;

  @NotBlank(message = "주요 활동 지역은 필수 입력 값입니다.")
  private String mainArea;

  @NotBlank(message = "동아리 대표 사진은 필수 입력 값입니다.")
  private String profileImageUrl;

  @NotBlank(message = "대학교는 필수 입력 값입니다.")
  private Long universityId;

  @NotBlank(message = "종목은 필수 입력 값입니다.")
  private Long sportEventId;
}
