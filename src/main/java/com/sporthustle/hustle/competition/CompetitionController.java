package com.sporthustle.hustle.competition;

import com.sporthustle.hustle.common.annotation.UserId;
import com.sporthustle.hustle.competition.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Competition", description = "대회 API")
@Slf4j
@RestController
@RequestMapping("/api/competition")
@RequiredArgsConstructor
public class CompetitionController {

  private final CompetitionService competitionService;

  @Operation(summary = "대회 목록 조회 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 목록 조회에 성공한 경우"),
  })
  @GetMapping
  public ResponseEntity<CompetitionsResponseDTO> getCompetitionsByYearAndMonthAndState(
      @RequestParam(name = "sport_event_id", required = false) Long sportEventId,
      @RequestParam(name = "state", defaultValue = "ACTIVE")
          CompetitionStateRequest competitionStateRequest,
      @PageableDefault(size = 4, direction = Sort.Direction.DESC) Pageable pageable) {
    CompetitionsResponseDTO competitionsResponseDTO =
        competitionService.getCompetitionsByListType(
            sportEventId, competitionStateRequest, pageable);
    return ResponseEntity.ok(competitionsResponseDTO);
  }

  @Operation(summary = "대회 개설 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 수정에 성공한 경우"),
  })
  @PostMapping
  public ResponseEntity<CreateCompetitionResponseDTO> createCompetition(
      @UserId Long userId, @RequestBody CreateCompetitionRequestDTO createCompetitionRequestDTO) {
    CreateCompetitionResponseDTO createCompetitionResponseDTO =
        competitionService.createCompetition(userId, createCompetitionRequestDTO);
    return ResponseEntity.ok(createCompetitionResponseDTO);
  }

  @Operation(summary = "대회 상세 조회 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 상세 조회에 성공한 경우"),
  })
  @GetMapping("/{competition_id}")
  public ResponseEntity<CompetitionResponseDTO> getCompetition(
      @PathVariable("competition_id") Long competitionId) {
    CompetitionResponseDTO competitionResponseDTO =
        competitionService.getCompetition(competitionId);
    return ResponseEntity.ok(competitionResponseDTO);
  }

  @Operation(summary = "대회 수정 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 수정에 성공한 경우"),
  })
  @PatchMapping("/{competition_id}")
  public ResponseEntity<UpdateCompetitionResponseDTO> updateCompetition(
      @UserId Long userId,
      @PathVariable("competition_id") Long competitionId,
      @RequestBody UpdateCompetitionRequestDTO updateCompetitionRequestDTO) {
    UpdateCompetitionResponseDTO updateCompetitionResponseDTO =
        competitionService.updateCompetition(userId, competitionId, updateCompetitionRequestDTO);
    return ResponseEntity.ok(updateCompetitionResponseDTO);
  }

  @Operation(summary = "대회 삭제 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 삭제에 성공한 경우"),
  })
  @DeleteMapping("/{competition_id}")
  public ResponseEntity<DeleteCompetitionResponseDTO> deleteCompetition(
      @UserId Long userId, @PathVariable("competition_id") Long competitionId) {
    DeleteCompetitionResponseDTO deleteCompetitionResponseDTO =
        competitionService.deleteCompetition(userId, competitionId);
    return ResponseEntity.ok(deleteCompetitionResponseDTO);
  }

  @Operation(summary = "HOT 대회 조회 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "HOT 대회 목록 조회에 성공한 경우"),
  })
  @GetMapping("/popular")
  public ResponseEntity<Object> getPopularCompetitions(
      @PageableDefault(size = 5, direction = Sort.Direction.DESC) Pageable pageable) {
    CompetitionsResponseDTO competitionsResponseDTO =
        competitionService.getPopularCompetitions(pageable);
    return ResponseEntity.ok(competitionsResponseDTO);
  }
}
