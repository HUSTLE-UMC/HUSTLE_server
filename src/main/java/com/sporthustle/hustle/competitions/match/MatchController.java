package com.sporthustle.hustle.competitions.match;

import com.sporthustle.hustle.common.annotation.UserId;
import com.sporthustle.hustle.competitions.ingame.dto.InGameCategory;
import com.sporthustle.hustle.competitions.match.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Competition Match Result Post", description = "대회 경기 결과 API")
@Slf4j
@RestController
@RequestMapping("/api/competition")
@RequiredArgsConstructor
public class MatchController {

  private final MatchService matchService;

  @Operation(summary = "대회 경기 결과 목록 조회 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 경기 결과 목록 조회에 성공한 경우"),
  })
  @GetMapping("/{competition_id}/{category}/matchresultpost")
  public ResponseEntity<MatchResultPostsResponseDTO> getMatchResultPosts(
      @PathVariable("competition_id") Long competitionId,
      @PathVariable("category") InGameCategory inGameCategory,
      @RequestParam("group_category") String groupCategory) {
    MatchResultPostsResponseDTO matchResultPostsResponseDTO =
        matchService.getMatchResultPosts(competitionId, inGameCategory, groupCategory);
    return ResponseEntity.ok(matchResultPostsResponseDTO);
  }

  @Operation(summary = "대회 경기 결과 생성 (개설자) API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 경기 결과 생성에 성공한 경우"),
    @ApiResponse(responseCode = "403", description = "대회 개설자가 아닌 경우"),
  })
  @PostMapping("/{competition_id}/{category}/matchresultpost")
  public ResponseEntity<CreateMatchResultPostResponseDTO> createMatchResultPost(
      @UserId Long userId,
      @PathVariable("competition_id") Long competitionId,
      @PathVariable("category") InGameCategory inGameCategory,
      @RequestBody CreateMatchResultPostRequestDTO createMatchResultPostRequestDTO) {
    CreateMatchResultPostResponseDTO createMatchResultPostResponseDTO =
        matchService.createMatchResultPost(
            userId, competitionId, inGameCategory, createMatchResultPostRequestDTO);
    return ResponseEntity.ok(createMatchResultPostResponseDTO);
  }

  @Operation(summary = "대회 경기 결과 조회 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 경기 결과 조회에 성공한 경우"),
    @ApiResponse(responseCode = "400", description = "대회 경기 결과를 찾을 수 없는 경우"),
  })
  @GetMapping("/{competition_id}/{category}/matchresultpost/{match_result_post_id}")
  public ResponseEntity<MatchResultPostResponseDTO> getMatchResultPost(
      @PathVariable("competition_id") Long competitionId,
      @PathVariable("category") InGameCategory inGameCategory,
      @PathVariable("match_result_post_id") Long matchResultPostId) {
    MatchResultPostResponseDTO matchResultPostResponseDTO =
        matchService.getMatchResultPost(competitionId, inGameCategory, matchResultPostId);
    return ResponseEntity.ok(matchResultPostResponseDTO);
  }

  @Operation(summary = "대회 경기 결과 수정 (개설자) API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 경기 결과 수정에 성공한 경우"),
    @ApiResponse(responseCode = "400", description = "대회 경기 결과를 찾을 수 없는 경우"),
    @ApiResponse(responseCode = "403", description = "대회 개설자가 아닌 경우"),
  })
  @PatchMapping("/{competition_id}/{category}/matchresultpost/{match_result_post_id}")
  public ResponseEntity<UpdateMatchResultPostResponseDTO> updateMatchResultPost(
      @UserId Long userId,
      @PathVariable("competition_id") Long competitionId,
      @PathVariable("category") InGameCategory inGameCategory,
      @PathVariable("match_result_post_id") Long matchResultPostId,
      @RequestBody UpdateMatchResultPostRequestDTO updateMatchResultPostRequestDTO) {
    UpdateMatchResultPostResponseDTO updateMatchResultPostResponseDTO =
        matchService.updateMatchResultPost(
            userId,
            competitionId,
            inGameCategory,
            matchResultPostId,
            updateMatchResultPostRequestDTO);
    return ResponseEntity.ok(updateMatchResultPostResponseDTO);
  }

  @Operation(summary = "대회 경기 결과 삭제 (개설자) API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 경기 결과 삭제에 성공한 경우"),
    @ApiResponse(responseCode = "400", description = "대회 경기 결과를 찾을 수 없는 경우"),
    @ApiResponse(responseCode = "403", description = "대회 개설자가 아닌 경우"),
  })
  @DeleteMapping("/{competition_id}/{category}/matchresultpost/{match_result_post_id}")
  public ResponseEntity<DeleteMatchResultPostResponseDTO> deleteMatchResultPost(
      @UserId Long userId,
      @PathVariable("competition_id") Long competitionId,
      @PathVariable("category") InGameCategory inGameCategory,
      @PathVariable("match_result_post_id") Long matchResultPostId) {
    DeleteMatchResultPostResponseDTO deleteMatchResultPostResponseDTO =
        matchService.deleteMatchResultPost(
            userId, competitionId, inGameCategory, matchResultPostId);
    return ResponseEntity.ok(deleteMatchResultPostResponseDTO);
  }
}
