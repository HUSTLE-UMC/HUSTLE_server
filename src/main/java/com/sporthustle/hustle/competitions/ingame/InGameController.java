package com.sporthustle.hustle.competitions.ingame;

import com.sporthustle.hustle.competitions.ingame.dto.*;
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

  private final InGameService inGameService;

  @Operation(summary = "대회 예선 조 목록 조회 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 예선 조 목록 조회에 성공한 경우"),
    @ApiResponse(responseCode = "400", description = "대회 개설자가 아닌 경우"),
  })
  @GetMapping("/{competition_id}/preround/group")
  public ResponseEntity<PreRoundGroupsResponseDTO> getPreRoundGroups(
      @PathVariable("competition_id") Long competitionId) {
    PreRoundGroupsResponseDTO preRoundGroupsResponseDTO =
        inGameService.getPreRoundGroups(competitionId);
    return ResponseEntity.ok(preRoundGroupsResponseDTO);
  }

  @Operation(summary = "대회 예선 / 본선 카테고리 조회 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 예선 / 본선 카테고리 조회에 성공한 경우"),
  })
  @GetMapping("/{competition_id}/{type}/category")
  public ResponseEntity<CategoriesResponseDTO> getCategories(
      @PathVariable("competition_id") Long competitionId, @PathVariable("type") InGameType type) {
    CategoriesResponseDTO categoriesResponseDTO = inGameService.getCategories(competitionId, type);
    return ResponseEntity.ok(categoriesResponseDTO);
  }
}
