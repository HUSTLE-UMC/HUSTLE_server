package com.sporthustle.hustle.user.dto;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class UpdateMyProfileRequestDTO {

  private String password;

  private String name;

  private LocalDate birthday;

  private String gender;

  private Long universityId;
}
