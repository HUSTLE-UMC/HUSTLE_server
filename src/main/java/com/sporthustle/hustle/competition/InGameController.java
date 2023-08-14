package com.sporthustle.hustle.competition;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Competition InGame", description = "대회 인게임 예선 / 본선 API")
@Slf4j
@RestController
@RequestMapping("/api/competition")
@RequiredArgsConstructor
public class InGameController {

  @Operation(summary = "대회 예선 조 목록 조회 (개설자) API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 예선 조 목록 조회에 성공한 경우"),
  })
  @GetMapping("/{competition_id}/preround/group")
  public ResponseEntity<Object> getPreRoundGroups(
      @PathVariable("competition_id") Long competitionId) {
    return ResponseEntity.ok(null);
  }

  @Operation(summary = "대회 예선 조 편성 (개설자) API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 예선 조 목록 조회에 성공한 경우"),
  })
  @PostMapping("/{competition_id}/preround/group/pick")
  public ResponseEntity<Object> pickPreRoundGroups(
      @PathVariable("competition_id") Long competitionId) {
    return ResponseEntity.ok(null);
  }

  @Operation(summary = "대회 예선 조 목록 생성 (개설자) API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 예선 조 목록 조회에 성공한 경우"),
  })
  @PostMapping("/{competition_id}/preround/group")
  public ResponseEntity<Object> createPreRoundGroups(
      @PathVariable("competition_id") Long competitionId) {
    return ResponseEntity.ok(null);
  }

  @Operation(summary = "대회 예선 / 본선 카테고리 조회 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 예선 조 목록 조회에 성공한 경우"),
  })
  @GetMapping("/{competition_id}/{type}/category")
  public ResponseEntity<Object> getCategories(
      @PathVariable("competition_id") Long competitionId, @PathVariable("type") String type) {
    return ResponseEntity.ok(null);
  }

  @Operation(summary = "대회 본선 조 목록 조회 (개설자) API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 예선 조 목록 조회에 성공한 경우"),
  })
  @GetMapping("/{competition_id}/finalround/team")
  public ResponseEntity<Object> getFinalRoundTeams(
      @PathVariable("competition_id") Long competitionId,
      @RequestParam("category") String category) {
    return ResponseEntity.ok(null);
  }

  @Operation(summary = "대회 예선 조 순위 조회 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 예선 조 목록 조회에 성공한 경우"),
  })
  @GetMapping("/{competition_id}/preround/ranking")
  public ResponseEntity<Object> getFinalRoundTeams(
      @PathVariable("competition_id") Long competitionId) {
    return ResponseEntity.ok(null);
  }
}
