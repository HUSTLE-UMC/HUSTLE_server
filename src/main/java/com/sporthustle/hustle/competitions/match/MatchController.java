package com.sporthustle.hustle.competitions.match;

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

  @Operation(summary = "대회 경기 결과 목록 조회 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 예선 조 목록 조회에 성공한 경우"),
  })
  @GetMapping("/{competition_id}/{type}/match_result_post")
  public ResponseEntity<Object> getMatchResultPosts(
      @PathVariable("competition_id") Long competitionId,
      @PathVariable("type") String type,
      @RequestParam("category") String category) {
    return ResponseEntity.ok(null);
  }

  @Operation(summary = "대회 경기 결과 생성 (개설자) API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 예선 조 목록 조회에 성공한 경우"),
  })
  @PostMapping("/{competition_id}/{type}/match_result_post")
  public ResponseEntity<Object> createMatchResultPost(
      @PathVariable("competition_id") Long competitionId,
      @PathVariable("type") String type,
      @RequestParam("category") String category) {
    return ResponseEntity.ok(null);
  }

  @Operation(summary = "대회 경기 결과 조회 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 예선 조 목록 조회에 성공한 경우"),
  })
  @GetMapping("/{competition_id}/{type}/match_result_post/{match_result_post_id}")
  public ResponseEntity<Object> getMatchResultPost(
      @PathVariable("competition_id") Long competitionId,
      @PathVariable("type") String type,
      @RequestParam("category") String category,
      @PathVariable("match_result_post_id") Long matchResultPostId) {
    return ResponseEntity.ok(null);
  }

  @Operation(summary = "대회 경기 결과 수정 (개설자) API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 예선 조 목록 조회에 성공한 경우"),
  })
  @PatchMapping("/{competition_id}/{type}/match_result_post/{match_result_post_id}")
  public ResponseEntity<Object> updateMatchResultPost(
      @PathVariable("competition_id") Long competitionId,
      @PathVariable("type") String type,
      @RequestParam("category") String category,
      @PathVariable("match_result_post_id") Long matchResultPostId) {
    return ResponseEntity.ok(null);
  }

  @Operation(summary = "대회 경기 결과 삭제 (개설자) API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대회 예선 조 목록 조회에 성공한 경우"),
  })
  @DeleteMapping("/{competition_id}/{type}/match_result_post/{match_result_post_id}")
  public ResponseEntity<Object> deleteMatchResultPost(
      @PathVariable("competition_id") Long competitionId,
      @PathVariable("type") String type,
      @RequestParam("category") String category,
      @PathVariable("match_result_post_id") Long matchResultPostId) {
    return ResponseEntity.ok(null);
  }
}
