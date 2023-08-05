package com.sporthustle.hustle.src.user.model;

import java.util.Date;
import lombok.Getter;

@Getter
public class FindAccountReq {
  private String name;
  private Date birth;
  private String email;
}
