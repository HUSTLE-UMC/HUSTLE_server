package com.sporthustle.hustle.club.dto;

import lombok.Getter;

@Getter
public class UpdateClubRequestDTO {

  private String name;

  private String instagram;

  private String youtubeUrl;

  private String mainArea;

  private String profileImageUrl;

  private Long universityId;

  private Long sportEventId;
}
