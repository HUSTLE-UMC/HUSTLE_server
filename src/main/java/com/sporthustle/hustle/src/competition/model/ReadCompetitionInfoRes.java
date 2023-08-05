package com.sporthustle.hustle.src.competition.model;

import lombok.Builder;

import javax.persistence.Column;
import java.sql.Timestamp;


@Builder
public class ReadCompetitionInfoRes {
    private Timestamp startDate;
    private Timestamp endDate;
    private Timestamp recruitmentStartDate;
    private Timestamp recruitmentEndDate;
    private int entryFee;
    private int maxEntryCount;
    private String sponsor;
    private String place;
    private String mainPhoneNumber;
    private String subPhoneNumber;
    //private int numberOfTeam;
    //private List<ClubRes> clubResList; 추후 구현
}
