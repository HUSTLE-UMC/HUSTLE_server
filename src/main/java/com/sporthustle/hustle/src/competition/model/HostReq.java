package com.sporthustle.hustle.src.competition.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter
public class HostReq {

    @NotBlank(message = "대회이름은 필수 입력 값입니다.")
    private String title;

    @NotBlank(message = "주최는 필수 입력 값입니다.")
    private String host;

    private String posterUrl;

    @NotBlank(message = "대회시작일은 필수 입력 값입니다.")
    private Timestamp startDate;

    @NotBlank(message = "대회종료일은 필수 입력 값입니다.")
    private Timestamp endDate;

    @NotBlank(message = "모집시작일은 필수 입력 값입니다.")
    private Timestamp recruitmentStartDate;

    @NotBlank(message = "모집종료일은 필수 입력 값입니다.")
    private Timestamp recruitmentEndDate;

    @NotBlank(message = "참가비는 필수 입력 값입니다.")
    private int entryFee;

    @NotBlank(message = "모집팀수는 필수 입력 값입니다.")
    private short maxEntryCount;

    @NotBlank(message = "예선 조 개수는 필수 입력 값입니다.")
    private int preRoundGroupCount;

    @NotBlank(message = "본선진출팀수는 필수 입력 값입니다.")
    private int finalRoundTeamCount;

    private String sponsor;

    @NotBlank(message = "장소는 필수 입력 값입니다.")
    private String place;

    @NotBlank(message = "회장이름은 필수 입력 값입니다.")
    private String mainName;

    @NotBlank(message = "회장연락처는 필수 입력 값입니다.")
    private String mainPhoneNumber;

    @NotBlank(message = "부회장이름은 필수 입력 값입니다.")
    private String subName;

    @NotBlank(message = "부회장연락처는 필수 입력 값입니다.")
    private String subPhoneNumber;

}
