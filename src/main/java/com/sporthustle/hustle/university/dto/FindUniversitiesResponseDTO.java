package com.sporthustle.hustle.university.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FindUniversitiesResponseDTO {

  private List<UniversityResponseDTO> universities;
}
