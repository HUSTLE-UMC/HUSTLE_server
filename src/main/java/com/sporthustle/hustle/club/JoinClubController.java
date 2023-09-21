package com.sporthustle.hustle.club;

import com.sporthustle.hustle.club.dto.*;
import com.sporthustle.hustle.common.annotation.UserId;
import com.sporthustle.hustle.competitions.entryteam.dto.EntryTeamsResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ClubJoin", description = "동아리 가입 도메인 API")
@Slf4j
@RestController
@RequestMapping("/api/club")
@RequiredArgsConstructor
public class JoinClubController {

    private final JoinClubService joinClubService;

    /*
    1. 본인의 가입 신청 목록 조회(GET)
    2. 동아리 가입 신청(POST)
    3. 동아리 신청 수락(PUT)
     */
    @Operation(summary = "동아리 가입 신청")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "동아리 가입 신청에 성공한 경우"),
    })
    @PostMapping("/{club_id}")
    public ResponseEntity<CreateJoinClubRequestResponseDTO> createJoinClubs(
            @PathVariable("club_id") Long clubId , @UserId Long userId){

                CreateJoinClubRequestResponseDTO createJoinClubRequestResponseDTO = joinClubService.createJoinClubResponseDTO(userId, clubId);
        return ResponseEntity.ok(createJoinClubRequestResponseDTO);
    }

    @Operation(summary = "동아리 신청자 목록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "동아리 신청자 조회에 성공한 경우"),
    })
    @GetMapping("/{club_id}")
    public ResponseEntity<JoinClubRequestsResponseDTO> getJoinRequests(
            @PathVariable("club_id") Long clubId) {
        JoinClubRequestsResponseDTO joinClubRequestsResponseDTO = joinClubService.getJoinRequests(clubId);
        return ResponseEntity.ok(joinClubRequestsResponseDTO);
    }


    @Operation(summary = "동아리 지원 수락 거절 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "동아리 지원 (수락/거절) 상태 변경에 성공한 경우"),
    })
    @PutMapping("/{club_id}")
    public ResponseEntity<UpdateJoinClubResponseDTO> updateFriendMatchingRequests(
            @PathVariable("club_id") Long clubId,
            @RequestBody
            UpdateJoinClubRequestDTO updateJoinClubRequestDTO) {

        UpdateJoinClubResponseDTO updateJoinClubResponseDTO =
                joinClubService.updateClubJoinRequest(clubId, updateJoinClubRequestDTO);
        return ResponseEntity.ok(updateJoinClubResponseDTO);
    }



}
