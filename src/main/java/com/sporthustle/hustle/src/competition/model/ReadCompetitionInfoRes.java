package com.sporthustle.hustle.src.competition.model;

import com.sporthustle.hustle.src.club.entity.Club;
import lombok.Builder;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.List;


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
    private int numberOfTeam;
    private List<Club> clubList;
}
