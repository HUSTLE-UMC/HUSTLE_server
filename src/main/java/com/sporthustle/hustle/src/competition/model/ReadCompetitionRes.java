package com.sporthustle.hustle.src.competition.model;

import lombok.Builder;

import java.sql.Time;
import java.sql.Timestamp;

@Builder
public class ReadCompetitionRes {
    private String posterUrl;
    private String title;
    private Timestamp startDate;
    private Timestamp endDate;
}
