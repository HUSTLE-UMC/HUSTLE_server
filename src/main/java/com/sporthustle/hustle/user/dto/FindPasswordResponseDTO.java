package com.sporthustle.hustle.user.dto;

import com.sporthustle.hustle.common.dto.BaseResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class FindPasswordResponseDTO extends BaseResponse<Boolean> {}
