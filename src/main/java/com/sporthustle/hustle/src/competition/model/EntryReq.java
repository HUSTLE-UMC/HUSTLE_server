package com.sporthustle.hustle.src.competition.model;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class EntryReq {
    @NotBlank(message = "동아리명은 필수 입력 값입니다.")
    private String clubName;
    @NotBlank(message = "대표자명은 필수 입력 값입니다.")
    private String mainName;
    @NotBlank(message = "연락처는 필수 입력 값입니다.")
    private String mainPhoneNumber;
}
