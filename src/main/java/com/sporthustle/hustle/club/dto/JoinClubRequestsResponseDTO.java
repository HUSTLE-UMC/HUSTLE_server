package com.sporthustle.hustle.club.dto;

import com.sporthustle.hustle.common.dto.BaseResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class JoinClubRequestsResponseDTO extends BaseResponse<List<JoinClubRequestResponseDTO>> {
}
