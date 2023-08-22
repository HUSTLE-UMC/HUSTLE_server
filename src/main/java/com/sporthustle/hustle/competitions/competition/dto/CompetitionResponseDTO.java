package com.sporthustle.hustle.competitions.competition.dto;

import com.sporthustle.hustle.competitions.competition.entity.Competition;
import com.sporthustle.hustle.competitions.competition.entity.CompetitionContact;
import com.sporthustle.hustle.sport.dto.SportEventResponseDTO;
import com.sporthustle.hustle.sport.entity.SportEvent;
import com.sporthustle.hustle.user.dto.CompetitionUserResponseDTO;
import com.sporthustle.hustle.user.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompetitionResponseDTO {

  private Long id;

  private String title;

  private String host;

  private String place;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private LocalDateTime recruitmentStartDate;

  private LocalDateTime recruitmentEndDate;

  private Long entryFee;

  private Integer maxEntryCount;

  private Integer entryCount;

  private String sponsor;

  private String posterUrl;

  private Integer preRoundGroupCount;

  private Integer finalRoundTeamCount;

  private CompetitionState competitionState;

  List<CompetitionContactResponseDTO> contacts;

  private CompetitionUserResponseDTO user;

  private SportEventResponseDTO sportEvent;

  public static CompetitionResponseDTO from(Competition competition) {
    User user = competition.getUser();
    CompetitionUserResponseDTO competitionUserResponseDTO = CompetitionUserResponseDTO.from(user);

    Set<CompetitionContact> contacts = competition.getContacts();
    List<CompetitionContactResponseDTO> contactResponseDTOs =
        contacts.stream().map(CompetitionContactResponseDTO::from).collect(Collectors.toList());

    SportEvent sportEvent = competition.getSportEvent();
    SportEventResponseDTO sportEventResponseDTO = SportEventResponseDTO.from(sportEvent);

    CompetitionState competitionState =
        CompetitionState.from(
            competition.getRecruitmentStartDate(),
            competition.getRecruitmentEndDate(),
            competition.getStartDate(),
            competition.getEndDate());

    return CompetitionResponseDTO.builder()
        .id(competition.getId())
        .title(competition.getTitle())
        .host(competition.getHost())
        .place(competition.getPlace())
        .startDate(competition.getStartDate())
        .endDate(competition.getEndDate())
        .recruitmentStartDate(competition.getRecruitmentStartDate())
        .recruitmentEndDate(competition.getRecruitmentEndDate())
        .entryFee(competition.getEntryFee())
        .maxEntryCount(competition.getMaxEntryCount())
        .entryCount(competition.getEntryCount())
        .sponsor(competition.getSponsor())
        .posterUrl(competition.getPosterUrl())
        .preRoundGroupCount(competition.getPreRoundGroupCount())
        .finalRoundTeamCount(competition.getFinalRoundTeamCount())
        .competitionState(competitionState)
        .contacts(contactResponseDTOs)
        .user(competitionUserResponseDTO)
        .sportEvent(sportEventResponseDTO)
        .build();
  }
}
