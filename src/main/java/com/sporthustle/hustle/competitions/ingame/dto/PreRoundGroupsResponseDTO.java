package com.sporthustle.hustle.competitions.ingame.dto;

import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PreRoundGroupsResponseDTO {

  public Map<String, List<PreRoundGroupResponseDTO>> preRoundGroups;
}
