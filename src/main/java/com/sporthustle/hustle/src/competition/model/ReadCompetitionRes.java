package com.sporthustle.hustle.src.competition.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sporthustle.hustle.src.club.entity.Club;
import com.sporthustle.hustle.src.club.model.ClubDTO;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReadCompetitionRes {
    private Long competitionId;
    private String posterUrl;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime recruitmentStartDate;
    private LocalDateTime recruitmentEndDate;
    private Long entryFee;
    private int maxEntryCount;
    private String sponsor;
    private String place;
    private List<ContactDTO> contactDTO;
    private int numberOfTeam;
    private List<ClubDTO> clubDTOS;
}
