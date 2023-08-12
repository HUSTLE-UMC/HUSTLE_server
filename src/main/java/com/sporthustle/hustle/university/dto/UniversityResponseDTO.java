package com.sporthustle.hustle.university.dto;

import com.sporthustle.hustle.university.entity.University;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UniversityResponseDTO {

  private Long id;
  private String name;
  private String address;

  public static UniversityResponseDTO from(University university) {
    return UniversityResponseDTO.builder()
        .id(university.getId())
        .name(university.getName())
        .address(university.getAddress())
        .build();
  }
}
