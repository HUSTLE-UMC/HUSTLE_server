package com.sporthustle.hustle.user.dto;

import com.sporthustle.hustle.club.dto.MyClubsResponseDTO;
import com.sporthustle.hustle.university.dto.UniversityResponseDTO;
import com.sporthustle.hustle.user.entity.Gender;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetMyProfileResponseDTO {

  private String name;

  private LocalDate birthday;

  private Gender gender;

  private UniversityResponseDTO university;

  private List<MyClubsResponseDTO> clubs;
}
