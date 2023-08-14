package com.sporthustle.hustle.club.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateClubResponseDTO {

  private String message;

  private ClubResponseDTO data;
}
