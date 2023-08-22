package com.sporthustle.hustle.competitions.match.dto;

import java.util.Map;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateMatchResultPostScoreLogRequestDTO {

  @NotBlank(message = "이름은 필수 입력 값입니다.")
  private String name;

  private Long userId;

  @NotNull(message = "득점은 필수 입력 값입니다.")
  private Long score;

  @NotNull(message = "경기 그룹 카테고리는 필수 입력 값입니다.")
  private Map<String, Object> extra;
}
