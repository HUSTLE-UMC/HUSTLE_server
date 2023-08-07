package com.sporthustle.hustle.src.competition.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class HostReq {

    @NotBlank(message = "대회이름은 필수 입력 값입니다.")
    private String title;

    @NotBlank(message = "주최는 필수 입력 값입니다.")
    private String host;

    @NotBlank(message = "대회포스터는 필수 입력 값입니다.")
    private String posterUrl;

    @NotBlank(message = "대회시작일은 필수 입력 값입니다.")
    private LocalDateTime startDate;

    @NotBlank(message = "대회종료일은 필수 입력 값입니다.")
    private LocalDateTime endDate;

    @NotBlank(message = "모집시작일은 필수 입력 값입니다.")
    private LocalDateTime recruitmentStartDate;

    @NotBlank(message = "모집종료일은 필수 입력 값입니다.")
    private LocalDateTime recruitmentEndDate;

    @NotBlank(message = "참가비는 필수 입력 값입니다.")
    private Long entryFee;

    @NotBlank(message = "모집팀수는 필수 입력 값입니다.")
    private int maxEntryCount;

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

    //@NotBlank(message = "부회장이름은 필수 입력 값입니다.")
    private String subName;

    //@NotBlank(message = "부회장연락처는 필수 입력 값입니다.")
    private String subPhoneNumber;

}
