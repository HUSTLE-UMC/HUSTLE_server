package com.sporthustle.hustle.user.dto;

import com.sporthustle.hustle.user.entity.User;
import lombok.Builder;
import lombok.Getter;

/*
대회 엔티티를 조회할 때 작성자 정보를 넘겨주기 위한 DTO 입니다.
아직 이를 구체적으로 사용하는 기획이 없으므로 구현을 보류합니다.
추후 작성자명을 보여줘야하는 등 또는 완전 익명으로 하는 등의 기획이 추가되면
위 주석을 제거하고 의도에 맞게 변경합니다.
*/

@Getter
@Builder
public class CompetitionUserResponseDTO {

  private Long id;

  public static CompetitionUserResponseDTO from(User user) {
    return CompetitionUserResponseDTO.builder().id(user.getId()).build();
  }
}
