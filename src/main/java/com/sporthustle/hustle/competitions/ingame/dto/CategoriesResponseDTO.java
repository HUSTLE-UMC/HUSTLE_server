package com.sporthustle.hustle.competitions.ingame.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoriesResponseDTO {

  private List<String> categories;
}
