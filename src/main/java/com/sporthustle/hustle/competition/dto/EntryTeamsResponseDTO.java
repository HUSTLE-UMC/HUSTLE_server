package com.sporthustle.hustle.competition.dto;

import com.sporthustle.hustle.club.dto.ClubResponseDTO;
import com.sporthustle.hustle.competition.entity.entryteam.EntryTeam;
import com.sporthustle.hustle.user.dto.UserResponseDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class EntryTeamsResponseDTO {

  private List<EntryTeamResponseDTO> entryTeams;
}
