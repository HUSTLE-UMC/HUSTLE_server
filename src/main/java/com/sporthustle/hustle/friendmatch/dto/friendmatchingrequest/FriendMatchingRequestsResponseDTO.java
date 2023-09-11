package com.sporthustle.hustle.friendmatch.dto.friendmatchingrequest;

import com.sporthustle.hustle.common.dto.BaseResponse;
import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class FriendMatchingRequestsResponseDTO
    extends BaseResponse<List<FriendMatchingRequestResponseDTO>> {}
