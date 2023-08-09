package com.sporthustle.hustle.src.club.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubDto {
    private Long id;
    private String name;
    private String universityName;
    private String mainArea;
    private String instagram;
    private String youtubeUrl;
    private String profileImageUrl;
    private int point;
}
