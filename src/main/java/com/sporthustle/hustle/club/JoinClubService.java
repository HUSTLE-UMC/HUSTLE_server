package com.sporthustle.hustle.club;

import com.sporthustle.hustle.club.dto.*;
import com.sporthustle.hustle.club.entity.Club;
import com.sporthustle.hustle.club.entity.ClubMember;
import com.sporthustle.hustle.club.entity.JoinClubRequest;
import com.sporthustle.hustle.club.entity.JoinClubRequestType;
import com.sporthustle.hustle.club.repository.ClubJoinRepository;
import com.sporthustle.hustle.club.repository.ClubMemberRepository;
import com.sporthustle.hustle.club.repository.ClubRepository;
import com.sporthustle.hustle.user.UserUtils;
import com.sporthustle.hustle.user.entity.User;
import com.sporthustle.hustle.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class JoinClubService {
    ClubJoinRepository clubJoinRepository;
    UserRepository userRepository;
    ClubRepository clubRepository;

    ClubMemberRepository clubMemberRepository;

    @Transactional(readOnly = true)
    public CreateJoinClubRequestResponseDTO createJoinClubResponseDTO(
            Long userId, Long clubId){
        User user = UserUtils.getUserById(userId,userRepository);
        Club club = ClubUtils.getClubById(clubId,clubRepository);
        JoinClubRequest joinClubRequest = JoinClubRequest.builder()
                .club(club)
                .user(user)
                .type(JoinClubRequestType.WAIT)
                .build();
        clubJoinRepository.save(joinClubRequest);

        JoinClubRequestResponseDTO joinClubRequestResponseDTO = JoinClubRequestResponseDTO.from(joinClubRequest);

        return CreateJoinClubRequestResponseDTO.builder()
                .code("SUCCESS_MAKE_JOIN_REQUEST")
                .message("동아리에 성공적으로 지원됐습니다.")
                .data(joinClubRequestResponseDTO)
                .build();
    }


    public JoinClubRequestsResponseDTO getJoinRequests(Long clubId) {
        List<JoinClubRequest> joinClubRequests = clubJoinRepository.findAllByClub_Id(clubId);
        List<JoinClubRequestResponseDTO> joinClubRequestsResponseDTO = joinClubRequests.stream().map(JoinClubRequestResponseDTO::from).collect(Collectors.toList());

        return JoinClubRequestsResponseDTO.builder()
                .code("SUCCESS_GET_CLUB_REQUESTS")
                .data(joinClubRequestsResponseDTO)
                .message("성공적으로 모든 가입 요청을 조회했습니다.")
                .build();
    }


    public UpdateJoinClubResponseDTO updateClubJoinRequest(
            Long clubId,
            UpdateJoinClubRequestDTO updateJoinClubRequestDTO) {
        JoinClubRequest joinClubRequest =
                ClubUtils.getJoinRequestById(
                        updateJoinClubRequestDTO.getClubJoinRequestId(),
                        clubJoinRepository);
        if (updateJoinClubRequestDTO.getJoinClubRequestType() == JoinClubRequestType.ACCEPT) {
            User user = joinClubRequest.getUser();
            Club club = ClubUtils.getClubById(clubId, clubRepository);
            ClubMember clubMember = ClubMember.builder().user(user).club(club).build();
            clubMemberRepository.save(clubMember);
        }
        else if (updateJoinClubRequestDTO.getJoinClubRequestType() == JoinClubRequestType.DECLINE) {
            joinClubRequest.updateType(updateJoinClubRequestDTO.getJoinClubRequestType());
        }

        JoinClubRequestResponseDTO joinClubRequestResponseDTO =
                JoinClubRequestResponseDTO.from(joinClubRequest);

        return UpdateJoinClubResponseDTO.builder()
                .code("SUCCESS_UPDATE_CLUB_REQUEST")
                .data(joinClubRequestResponseDTO)
                .message("성공적으로 가입 요청을 업데이트 했습니다.")
                .build();
    }
}
