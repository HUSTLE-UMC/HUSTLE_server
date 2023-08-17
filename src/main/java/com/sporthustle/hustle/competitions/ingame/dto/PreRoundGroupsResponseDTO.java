package com.sporthustle.hustle.competitions.ingame.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PreRoundGroupsResponseDTO {

  public List<PreRoundGroupResponseDTO> preRoundGroups;
}
