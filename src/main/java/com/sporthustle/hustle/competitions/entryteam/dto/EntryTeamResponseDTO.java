package com.sporthustle.hustle.competitions.entryteam.dto;

import com.sporthustle.hustle.club.dto.ClubResponseDTO;
import com.sporthustle.hustle.competitions.entryteam.entity.EntryTeam;
import com.sporthustle.hustle.user.dto.UserResponseDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EntryTeamResponseDTO {

  private Long id;

  private String name;

  private String phoneNumber;

  private Long score;

  private Integer matchCount;

  private Integer winCount;

  private Integer loseCount;

  private Integer drawCount;

  private Integer scoreDifferenceCount;

  private UserResponseDTO user;

  private ClubResponseDTO club;

  public static EntryTeamResponseDTO from(EntryTeam entryTeam) {
    return EntryTeamResponseDTO.builder()
        .id(entryTeam.getId())
        .name(entryTeam.getName())
        .phoneNumber(entryTeam.getPhoneNumber())
        .score(entryTeam.getScore())
        .matchCount(entryTeam.getMatchCount())
        .winCount(entryTeam.getWinCount())
        .loseCount(entryTeam.getLoseCount())
        .drawCount(builder().drawCount)
        .scoreDifferenceCount(entryTeam.getScoreDifferenceCount())
        .user(UserResponseDTO.from(entryTeam.getUser(), null))
        .club(ClubResponseDTO.from(entryTeam.getClub()))
        .build();
  }
}
