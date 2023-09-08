package com.sporthustle.hustle.competitions.match.dto;

import com.sporthustle.hustle.common.dto.BaseResponse;
import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class MatchResultPostsResponseDTO extends BaseResponse<List<MatchResultPostResponseDTO>> {}
