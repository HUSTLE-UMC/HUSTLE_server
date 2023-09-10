package com.sporthustle.hustle.university.dto;

import com.sporthustle.hustle.common.dto.BaseResponse;
import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class FindUniversitiesResponseDTO extends BaseResponse<List<UniversityResponseDTO>> {}
