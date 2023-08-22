package com.sporthustle.hustle.competitions.match.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateMatchRequestPostsRequestDTO {

  @NotBlank(message = "경기 그룹 카테고리는 필수 입력 값입니다.")
  private String groupCategory;

  private List<CreateMatchResultPostRequestDTO> matchResultPosts;
}
