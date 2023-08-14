package com.sporthustle.hustle.club.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClubsResponseDTO {

  List<ClubResponseDTO> clubs;
}
