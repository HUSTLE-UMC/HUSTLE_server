package com.sporthustle.hustle.user.dto;

import com.sporthustle.hustle.club.dto.MyClubsResponseDTO;
import com.sporthustle.hustle.common.entity.BaseStatus;
import com.sporthustle.hustle.university.dto.UniversityResponseDTO;
import com.sporthustle.hustle.university.entity.University;
import com.sporthustle.hustle.user.entity.Gender;
import com.sporthustle.hustle.user.entity.SnsType;
import com.sporthustle.hustle.user.entity.User;
import com.sporthustle.hustle.user.entity.UserRole;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDTO {

  private Long id;

  private String email;

  private String name;

  private LocalDate birthday;

  private Gender gender;

  private Boolean isMailing;

  private SnsType snsType;

  private BaseStatus status;

  private UserRole userRole;

  private UniversityResponseDTO university;

  private List<MyClubsResponseDTO> clubs;

  public static UserResponseDTO from(User user, List<MyClubsResponseDTO> myClubsResponseDTOs) {
    University university = user.getUniversity();
    UniversityResponseDTO universityResponseDTO =
        UniversityResponseDTO.builder()
            .id(university.getId())
            .name(university.getName())
            .address(university.getAddress())
            .build();

    return UserResponseDTO.builder()
        .id(user.getId())
        .email(user.getEmail())
        .name(user.getName())
        .birthday(user.getBirthday())
        .gender(user.getGender())
        .isMailing(user.getIsMailing())
        .snsType(user.getSnsType())
        .status(user.getStatus())
        .userRole(user.getUserRole())
        .university(universityResponseDTO)
        .clubs(myClubsResponseDTOs)
        .build();
  }
}
