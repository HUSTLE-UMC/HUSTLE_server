package com.sporthustle.hustle.community.club.post;

import com.sporthustle.hustle.common.annotation.UserId;
import com.sporthustle.hustle.community.club.post.dto.*;
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

@Tag(name = "ClubPost", description = "동아리 게시글 API")
@Slf4j
@RestController
@RequestMapping("/api/community/club")
@RequiredArgsConstructor
public class ClubPostController {

  private final ClubPostService clubPostService;

  @Operation(summary = "동아리 게시글 생성 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "동아리 게시글을 성공적으로 생성하는 경우"),
  })
  @PostMapping("/{club_id}")
  public ResponseEntity<CreateClubPostResponseDTO> createClubPost(
      @UserId Long userId,
      @PathVariable("club_id") Long clubId,
      @RequestBody CreateClubPostRequestDTO createClubPostRequestDTO) {
    CreateClubPostResponseDTO createClubPostResponseDTO =
        clubPostService.createClubPost(userId, clubId, createClubPostRequestDTO);
    return ResponseEntity.ok(createClubPostResponseDTO);
  }

  @Operation(summary = "동아리 게시글 수정 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "동아리 게시글을 성공적으로 수정하는 경우"),
  })
  @PatchMapping("/{club_id}/{club_post_id}")
  public ResponseEntity<UpdateClubPostResponseDTO> updateClubPost(
      @UserId Long userId,
      @PathVariable("club_id") Long clubId,
      @PathVariable("club_post_id") Long clubPostId,
      @RequestBody UpdateClubPostRequestDTO updateClubPostRequestDTO) {
    UpdateClubPostResponseDTO updateClubPostResponseDTO =
        clubPostService.updateClubPost(userId, clubId, clubPostId, updateClubPostRequestDTO);
    return ResponseEntity.ok(updateClubPostResponseDTO);
  }

  @Operation(summary = "동아리 게시글 삭제 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "동아리 게시글을 성공적으로 삭제하는 경우"),
  })
  @DeleteMapping("/{club_id}/{club_post_id}")
  public ResponseEntity<DeleteClubPostResponseDTO> deleteClubPost(
      @UserId Long userId,
      @PathVariable("club_id") Long clubId,
      @PathVariable("club_post_id") Long clubPostId) {
    DeleteClubPostResponseDTO deleteClubPostResponseDTO =
        clubPostService.deleteClubPost(userId, clubId, clubPostId);
    return ResponseEntity.ok(deleteClubPostResponseDTO);
  }

  @Operation(summary = "동아리 게시글 목록 조회 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "동아리 게시글 목록을 성공적으로 조회하는 경우"),
  })
  @GetMapping("/{club_id}")
  public ResponseEntity<ClubPostsResponseDTO> getClubPosts(
      @UserId Long userId,
      @PathVariable("club_id") Long clubId,
      @PageableDefault(size = 10, direction = Sort.Direction.DESC) Pageable pageable) {
    ClubPostsResponseDTO clubPostsResponseDTO =
        clubPostService.getClubPosts(userId, clubId, pageable);
    return ResponseEntity.ok(clubPostsResponseDTO);
  }

  @Operation(summary = "동아리 게시글 목록 조회 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "동아리 게시글 목록을 성공적으로 조회하는 경우"),
  })
  @GetMapping("/{club_id}/{club_post_id}")
  public ResponseEntity<GetClubPostResponseDTO> getClubPosts(
      @UserId Long userId,
      @PathVariable("club_id") Long clubId,
      @PathVariable("club_post_id") Long clubPostId,
      @PageableDefault(size = 10, direction = Sort.Direction.DESC) Pageable pageable) {
    GetClubPostResponseDTO getClubPostResponseDTO =
        clubPostService.getClubPost(userId, clubId, clubPostId);
    return ResponseEntity.ok(getClubPostResponseDTO);
  }
}
