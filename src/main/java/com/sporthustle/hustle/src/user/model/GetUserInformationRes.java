package com.sporthustle.hustle.src.user.model;

import com.sporthustle.hustle.src.user.entity.Gender;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetUserInformationRes {

  private String name;
  private Date birth;
  private Gender gender;
  private Long universityId;
}
