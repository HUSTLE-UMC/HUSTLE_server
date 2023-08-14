package com.sporthustle.hustle.competition;

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

  @Operation(summary = "대회 세부 정보 조회 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 참가팀 조회에 성공한 경우"),
  })
  @GetMapping("/{competition_id}/{type}/detail")
  public ResponseEntity<Object> getDetail(
      @PathVariable("competition_id") Long competitionId, @PathVariable("type") String type) {
    return ResponseEntity.ok(null);
  }

  @Operation(summary = "대회 세부 정보 업데이트 (개설자) API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 참가팀 조회에 성공한 경우"),
  })
  @PutMapping("/{competition_id}/{type}/detail")
  public ResponseEntity<Object> updateDetail(
      @PathVariable("competition_id") Long competitionId, @PathVariable("type") String type) {
    return ResponseEntity.ok(null);
  }
}
