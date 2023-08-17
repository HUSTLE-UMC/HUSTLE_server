package com.sporthustle.hustle.competitions.ingame;

import com.sporthustle.hustle.common.annotation.UserId;
import com.sporthustle.hustle.competitions.ingame.dto.DetailResponseDTO;
import com.sporthustle.hustle.competitions.ingame.dto.InGameType;
import com.sporthustle.hustle.competitions.ingame.dto.UpdateDetailRequestDTO;
import com.sporthustle.hustle.competitions.ingame.dto.UpdateDetailResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Competition Detail", description = "대회 세부 정보 API")
@Slf4j
@RestController
@RequestMapping("/api/competition")
@RequiredArgsConstructor
public class CompetitionDetailController {

  private final CompetitionDetailService competitionDetailService;

  @Operation(summary = "대회 세부 정보 조회 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 참가팀 조회에 성공한 경우"),
  })
  @GetMapping("/{competition_id}/{type}/detail")
  public ResponseEntity<DetailResponseDTO> getDetail(
      @PathVariable("competition_id") Long competitionId, @PathVariable("type") InGameType type) {
    DetailResponseDTO detailResponseDTO = competitionDetailService.getDetail(competitionId, type);
    return ResponseEntity.ok(detailResponseDTO);
  }

  @Operation(summary = "대회 세부 정보 업데이트 (개설자) API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 참가팀 수정에 성공한 경우"),
  })
  @PutMapping("/{competition_id}/{type}/detail")
  public ResponseEntity<UpdateDetailResponseDTO> updateDetail(
      @UserId Long userId,
      @PathVariable("competition_id") Long competitionId,
      @PathVariable("type") InGameType type,
      @RequestBody UpdateDetailRequestDTO updateDetailRequestDTO) {
    UpdateDetailResponseDTO updateDetailResponseDTO =
        competitionDetailService.updateDetail(userId, competitionId, type, updateDetailRequestDTO);
    return ResponseEntity.ok(updateDetailResponseDTO);
  }
}
