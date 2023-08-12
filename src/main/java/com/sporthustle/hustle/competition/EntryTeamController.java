package com.sporthustle.hustle.competition;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Competition Entry Team", description = "대회 참가 팀 API")
@Slf4j
@RestController
@RequestMapping("/api/competition")
@RequiredArgsConstructor
public class EntryTeamController {

  @Operation(summary = "대회 참가팀 조회 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 참가팀 조회에 성공한 경우"),
  })
  @GetMapping("/{competition_id}/entry_team")
  public ResponseEntity<Object> deleteEntryTeam(
      @PathVariable("competition_id") Long competitionId) {
    return ResponseEntity.ok(null);
  }

  @Operation(summary = "대회 참가 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 참가에 성공한 경우"),
  })
  @PostMapping("/{competition_id}/entry_team")
  public ResponseEntity<Object> createEntryTeam(
      @PathVariable("competition_id") Long competitionId) {
    return ResponseEntity.ok(null);
  }

  @Operation(summary = "대회 참가 취소 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 참가에 성공한 경우"),
  })
  @DeleteMapping("/{competition_id}/entry_team/{entry_team_id}")
  public ResponseEntity<Object> deleteEntryTeam(
      @PathVariable("competition_id") Long competitionId,
      @PathVariable("entry_team_id") Long entryTeamId) {
    return ResponseEntity.ok(null);
  }
}
