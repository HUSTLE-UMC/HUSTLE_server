package com.sporthustle.hustle.club.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetClubMembersResponseDTO {

  private List<ClubMemberResponseDTO> clubMembers;
}
