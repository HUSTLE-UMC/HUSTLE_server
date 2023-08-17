package com.sporthustle.hustle.competitions.ingame.dto;

import com.sporthustle.hustle.competitions.ingame.entity.PreRoundGroup;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PreRoundGroupsResponseDTO {

  public List<PreRoundGroup> preRoundGroups;
}
