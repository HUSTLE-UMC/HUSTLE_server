package com.sporthustle.hustle.club.dto;

import com.sporthustle.hustle.common.dto.BaseResponse;
import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class GetClubMembersResponseDTO extends BaseResponse<List<ClubMemberResponseDTO>> {}
