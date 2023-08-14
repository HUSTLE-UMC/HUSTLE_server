package com.sporthustle.hustle.club.dto;

import com.sporthustle.hustle.club.entity.Club;
import com.sporthustle.hustle.sport.dto.SportEventResponseDTO;
import com.sporthustle.hustle.sport.entity.SportEvent;
import com.sporthustle.hustle.university.dto.UniversityResponseDTO;
import com.sporthustle.hustle.university.entity.University;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClubResponseDTO {

  private Long id;

  private String name;

  private String instagram;

  private String youtubeUrl;

  private String mainArea;

  private String profileImageUrl;

  private long point;

  private UniversityResponseDTO university;

  private SportEventResponseDTO sportEvent;

  public static ClubResponseDTO from(Club club) {
    University university = club.getUniversity();
    UniversityResponseDTO universityResponseDTO =
        UniversityResponseDTO.builder()
            .id(university.getId())
            .name(university.getName())
            .address(university.getAddress())
            .build();

    SportEvent sportEvent = club.getSportEvent();
    SportEventResponseDTO sportEventResponseDTO = SportEventResponseDTO.from(sportEvent);

    return ClubResponseDTO.builder()
        .id(club.getId())
        .name(club.getName())
        .instagram(club.getInstagram())
        .youtubeUrl(club.getYoutubeUrl())
        .mainArea(club.getMainArea())
        .profileImageUrl(club.getProfileImageUrl())
        .point(club.getPoint())
        .university(universityResponseDTO)
        .sportEvent(sportEventResponseDTO)
        .build();
  }
}
