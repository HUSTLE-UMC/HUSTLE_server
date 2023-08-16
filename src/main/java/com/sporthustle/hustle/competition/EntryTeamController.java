package com.sporthustle.hustle.competition;

import com.sporthustle.hustle.common.annotation.UserId;
import com.sporthustle.hustle.competition.dto.CreateEntryTeamRequestDTO;
import com.sporthustle.hustle.competition.dto.CreateEntryTeamResponseDTO;
import com.sporthustle.hustle.competition.dto.DeleteEntryTeamResponseDTO;
import com.sporthustle.hustle.competition.dto.EntryTeamsResponseDTO;
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

  private final EntryTeamService entryTeamService;

  @Operation(summary = "대회 참가팀 조회 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 참가팀 조회에 성공한 경우"),
  })
  @GetMapping("/{competition_id}/entry_team")
  public ResponseEntity<EntryTeamsResponseDTO> getEntryTeam(
      @PathVariable("competition_id") Long competitionId) {
    EntryTeamsResponseDTO entryTeamsResponseDTO = entryTeamService.getEntryTeams(competitionId);
    return ResponseEntity.ok(entryTeamsResponseDTO);
  }

  @Operation(summary = "대회 참가 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 참가에 성공한 경우"),
  })
  @PostMapping("/{competition_id}/entry_team")
  public ResponseEntity<CreateEntryTeamResponseDTO> createEntryTeam(
          @UserId Long userId,
          @PathVariable("competition_id") Long competitionId,
          @RequestBody CreateEntryTeamRequestDTO createEntryTeamRequestDTO) {
    CreateEntryTeamResponseDTO createEntryTeamResponseDTO = entryTeamService.createEntryTeam(userId, competitionId, createEntryTeamRequestDTO);
    return ResponseEntity.ok(createEntryTeamResponseDTO);
  }

  @Operation(summary = "대회 참가 취소 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 참가에 성공한 경우"),
  })
  @DeleteMapping("/{competition_id}/entry_team")
  public ResponseEntity<DeleteEntryTeamResponseDTO> deleteEntryTeam(
      @UserId Long userId,
      @PathVariable("competition_id") Long competitionId) {
    DeleteEntryTeamResponseDTO deleteEntryTeamResponseDTO = entryTeamService.deleteEntryTeam(userId, competitionId);
    return ResponseEntity.ok(deleteEntryTeamResponseDTO);
  }
}
