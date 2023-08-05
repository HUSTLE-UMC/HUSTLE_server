package com.sporthustle.hustle.src.user.model;

import com.sporthustle.hustle.src.user.entity.Gender;
import java.util.Date;
import lombok.Getter;

@Getter
public class ModifyUserInfoReq {

  private String password;
  private String name;
  private Date birth;
  private Gender gender;
  private Long universityId;
}
