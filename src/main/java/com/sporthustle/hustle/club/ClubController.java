package com.sporthustle.hustle.club;

import com.sporthustle.hustle.club.dto.*;
import com.sporthustle.hustle.common.annotation.UserId;
import com.sporthustle.hustle.common.dto.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Club", description = "동아리 API")
@Slf4j
@RestController
@RequestMapping("/api/club")
@RequiredArgsConstructor
public class ClubController {

  private final ClubService clubService;

  @Operation(summary = "동아리 목록 검색 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "동아리 목록 검색에 성공한 경우"),
  })
  @GetMapping
  public ResponseEntity<BaseResponse<List<ClubResponseDTO>>> findClubsInUniversity(
      @RequestParam Long universityId, @RequestParam String keyword) {
    List<ClubResponseDTO> clubs = clubService.findClubsInUniversity(universityId, keyword);
    return ResponseEntity.ok(
        BaseResponse.<List<ClubResponseDTO>>builder()
            .code("SUCCESS_FIND_CLUBS")
            .message("동아리 검색에 성공했습니다.")
            .data(clubs)
            .build());
  }

  @Operation(summary = "동아리 개설 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "동아리 등록에 성공한 경우", useReturnTypeSchema = true),
    @ApiResponse(responseCode = "400", description = "파라미터 검증에 실패한 경우")
  })
  @PostMapping
  public ResponseEntity<CreateClubResponseDTO> createClub(
      @RequestBody CreateClubRequestDTO createClubRequestDTO) {
    CreateClubResponseDTO createClubResponseDTO = clubService.createClub(createClubRequestDTO);
    return ResponseEntity.ok(createClubResponseDTO);
  }

  @Operation(summary = "동아리 수정 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "동아리 수정에 성공한 경우"),
    @ApiResponse(responseCode = "400", description = "동아리 조회에 실패한 경우")
  })
  @PatchMapping("/{id}")
  public ResponseEntity<UpdateClubResponseDTO> updateClub(
      @PathVariable("id") Long clubId, @RequestBody UpdateClubRequestDTO updateClubRequestDTO) {
    UpdateClubResponseDTO updateClubResponseDTO =
        clubService.updateClub(clubId, updateClubRequestDTO);
    return ResponseEntity.ok(updateClubResponseDTO);
  }

  @Operation(summary = "동아리 삭제 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "동아리 삭제에 성공한 경우"),
    @ApiResponse(responseCode = "400", description = "동아리 조회에 실패한 경우")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<DeleteClubResponseDTO> deleteClub(@PathVariable("id") Long clubId) {
    DeleteClubResponseDTO deleteClubResponseDTO = clubService.deleteClub(clubId);
    return ResponseEntity.ok(deleteClubResponseDTO);
  }

  @Operation(summary = "동아리 회원 목록 조회 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "동아리 등록에 성공한 경우"),
    @ApiResponse(responseCode = "400", description = "파라미터 검증에 실패한 경우")
  })
  @GetMapping("/{club_id}/member")
  public ResponseEntity<GetClubMembersResponseDTO> getClubMembers(
      @UserId Long userId, @RequestParam("club_id") Long clubId) {
    GetClubMembersResponseDTO getClubMembersResponseDTO =
        clubService.getClubMembers(userId, clubId);
    return ResponseEntity.ok(getClubMembersResponseDTO);
  }

  @Operation(summary = "동아리 참여 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "동아리 등록에 성공한 경우"),
    @ApiResponse(responseCode = "400", description = "파라미터 검증에 실패한 경우")
  })
  @PostMapping("/{club_id}/member")
  public ResponseEntity<JoinClubResponseDTO> joinClub(
      @UserId Long userId, @PathVariable("club_id") Long clubId) {
    JoinClubResponseDTO joinClubResponseDTO = clubService.joinClub(userId, clubId);
    return ResponseEntity.ok(joinClubResponseDTO);
  }
}
