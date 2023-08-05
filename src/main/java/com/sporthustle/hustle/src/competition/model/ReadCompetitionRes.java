package com.sporthustle.hustle.src.competition.model;

import com.sporthustle.hustle.src.club.entity.Club;
import lombok.Builder;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Builder
public class ReadCompetitionRes {
    private String posterUrl;
    private String title;
    private Timestamp startDate;
    private Timestamp endDate;
    private List<Club> clubList;
}
