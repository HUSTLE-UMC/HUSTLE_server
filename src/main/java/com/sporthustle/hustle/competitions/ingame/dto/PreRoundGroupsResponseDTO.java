package com.sporthustle.hustle.competitions.ingame.dto;

import com.sporthustle.hustle.common.dto.BaseResponse;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class PreRoundGroupsResponseDTO
    extends BaseResponse<Map<String, List<PreRoundGroupResponseDTO>>> {}
