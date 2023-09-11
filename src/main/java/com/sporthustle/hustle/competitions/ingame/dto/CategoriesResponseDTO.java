package com.sporthustle.hustle.competitions.ingame.dto;

import com.sporthustle.hustle.common.dto.BaseResponse;
import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CategoriesResponseDTO extends BaseResponse<List<String>> {}
