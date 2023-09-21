package com.sporthustle.hustle.club.dto;


import com.sporthustle.hustle.club.entity.JoinClubRequest;
import com.sporthustle.hustle.club.entity.JoinClubRequestType;
import com.sporthustle.hustle.user.dto.JoinClubUserResponseDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JoinClubRequestResponseDTO {
    private Long id;
    private JoinClubRequestType type = JoinClubRequestType.WAIT;
    private JoinClubUserResponseDTO user;
    private ClubResponseDTO club;

    public static JoinClubRequestResponseDTO from (JoinClubRequest joinClubRequest){
        JoinClubUserResponseDTO user = JoinClubUserResponseDTO.from(joinClubRequest.getUser());
        ClubResponseDTO club = ClubResponseDTO.from(joinClubRequest.getClub());
        JoinClubRequestType type = joinClubRequest.getType();
        Long id = joinClubRequest.getId();

        return JoinClubRequestResponseDTO.builder()
                .user(user)
                .club(club)
                .type(type)
                .id(id)
                .build();
    }
}
