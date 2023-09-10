package com.sporthustle.hustle.competitions.entryteam.dto;

import com.sporthustle.hustle.common.dto.BaseResponse;
import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class EntryTeamsResponseDTO extends BaseResponse<List<EntryTeamResponseDTO>> {}
