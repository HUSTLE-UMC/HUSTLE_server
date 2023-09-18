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

@Tag(name = "ClubPostComment", description = "동아리 게시글 댓글 API")
@Slf4j
@RestController
@RequestMapping("/api/community/club")
@RequiredArgsConstructor
public class ClubPostCommentController {

  private final ClubPostCommentService clubPostCommentService;

  @Operation(summary = "동아리 게시글 댓글 생성 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "동아리 게시글 댓글을 성공적으로 생성하는 경우"),
  })
  @PostMapping("/{club_id}/{club_post_id}/comment")
  public ResponseEntity<CreateClubPostCommentResponseDTO> createClubPostComment(
      @UserId Long userId,
      @PathVariable("club_id") Long clubId,
      @PathVariable("club_post_id") Long clubPostId,
      @RequestBody CreateClubPostCommentRequestDTO createClubPostCommentRequestDTO) {
    CreateClubPostCommentResponseDTO createClubPostCommentResponseDTO =
        clubPostCommentService.createClubPostComment(
            userId, clubId, clubPostId, createClubPostCommentRequestDTO);
    return ResponseEntity.ok(createClubPostCommentResponseDTO);
  }

  @Operation(summary = "동아리 게시글 댓글 수정 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "동아리 게시글 댓글을 성공적으로 수정하는 경우"),
  })
  @PatchMapping("/{club_id}/{club_post_id}/comment/{club_post_comment_id}")
  public ResponseEntity<UpdateClubPostCommentResponseDTO> updateClubPostComment(
      @UserId Long userId,
      @PathVariable("club_id") Long clubId,
      @PathVariable("club_post_id") Long clubPostId,
      @PathVariable("club_post_comment_id") Long clubPostCommentId,
      @RequestBody UpdateClubPostCommentRequestDTO updateClubPostCommentRequestDTO) {
    UpdateClubPostCommentResponseDTO updateClubPostCommentResponseDTO =
        clubPostCommentService.updateClubPostComment(
            userId, clubId, clubPostId, clubPostCommentId, updateClubPostCommentRequestDTO);
    return ResponseEntity.ok(updateClubPostCommentResponseDTO);
  }

  @Operation(summary = "동아리 게시글 댓글 삭제 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "동아리 게시글 댓글을 성공적으로 삭제하는 경우"),
  })
  @DeleteMapping("/{club_id}/{club_post_id}/comment/{club_post_comment_id}")
  public ResponseEntity<DeleteClubPostCommentResponseDTO> deleteClubPostComment(
      @UserId Long userId,
      @PathVariable("club_id") Long clubId,
      @PathVariable("club_post_id") Long clubPostId,
      @PathVariable("club_post_comment_id") Long clubPostCommentId) {
    DeleteClubPostCommentResponseDTO deleteClubPostCommentResponseDTO =
        clubPostCommentService.deleteClubPostComment(userId, clubId, clubPostId, clubPostCommentId);
    return ResponseEntity.ok(deleteClubPostCommentResponseDTO);
  }

  @Operation(summary = "동아리 게시글 댓글 목록 조회 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "동아리 게시글 댓글 목록을 성공적으로 조회하는 경우"),
  })
  @GetMapping("/{club_id}/{club_post_id}/comment")
  public ResponseEntity<ClubPostCommentsResponseDTO> getClubPostComments(
      @UserId Long userId,
      @PathVariable("club_id") Long clubId,
      @PathVariable("club_post_id") Long clubPostId,
      @RequestParam(name = "type", defaultValue = "NEW") CommentSortType commentSortType) {
    ClubPostCommentsResponseDTO clubPostComments =
        clubPostCommentService.getClubPostComments(userId, clubId, clubPostId, commentSortType);
    return ResponseEntity.ok(clubPostComments);
  }
}
