package com.sporthustle.hustle.community.club.comment;

import com.sporthustle.hustle.common.annotation.UserId;
import com.sporthustle.hustle.community.club.comment.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ClubPostCommentLike", description = "동아리 게시글 댓글 추천 API")
@Slf4j
@RestController
@RequestMapping("/api/community/club")
@RequiredArgsConstructor
public class ClubPostCommentLikeController {

  private final ClubPostCommentLikeService clubPostCommentLikeService;

  @Operation(summary = "동아리 게시글 댓글 추천 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "동아리 게시글 댓글을 성공적으로 추천하는 경우"),
  })
  @PostMapping("/{club_id}/{club_post_id}/comment/{club_post_comment_id}/like")
  public ResponseEntity<GetClubPostCommentResponseDTO> createClubPostCommentLike(
      @UserId Long userId,
      @PathVariable("club_id") Long clubId,
      @PathVariable("club_post_id") Long clubPostId,
      @PathVariable("club_post_comment_id") Long clubPostCommentId) {
    GetClubPostCommentResponseDTO getClubPostCommentResponseDTO =
        clubPostCommentLikeService.createClubPostCommentLike(
            userId, clubId, clubPostId, clubPostCommentId);
    return ResponseEntity.ok(getClubPostCommentResponseDTO);
  }

  @Operation(summary = "동아리 게시글 댓글 추천 취소 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "동아리 게시글 댓글을 성공적으로 추천을 취소하는 경우"),
  })
  @DeleteMapping("/{club_id}/{club_post_id}/comment/{club_post_comment_id}/like")
  public ResponseEntity<GetClubPostCommentResponseDTO> deleteClubPostCommentLike(
      @UserId Long userId,
      @PathVariable("club_id") Long clubId,
      @PathVariable("club_post_id") Long clubPostId,
      @PathVariable("club_post_comment_id") Long clubPostCommentId) {
    GetClubPostCommentResponseDTO getClubPostCommentResponseDTO =
        clubPostCommentLikeService.deleteClubPostCommentLike(
            userId, clubId, clubPostId, clubPostCommentId);
    return ResponseEntity.ok(getClubPostCommentResponseDTO);
  }
}
