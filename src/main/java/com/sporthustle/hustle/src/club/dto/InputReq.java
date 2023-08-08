package com.sporthustle.hustle.src.club.dto;

import com.sporthustle.hustle.common.entity.BaseState;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.validation.constraints.NotBlank;
@Getter
public class InputReq {
    @NotBlank(message = "종목은 필수 입력 값입니다.")
    private Long sportEventId;
    @NotBlank(message = "동아리 명은 필수 입력 값입니다.")
    private String name;
    @NotBlank(message = "소속대학교는 필수 입력 값입니다.")
    private Long universityId;
    @NotBlank(message = "팀 주요 활동 지역은 필수 입력 값입니다.")
    private String mainArea;
    private String instagram;
    private String youtubeUrl;
    private String profileImageUrl;
    private BaseState state;
    private int point;
}
