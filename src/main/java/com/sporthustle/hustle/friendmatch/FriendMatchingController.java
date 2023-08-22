package com.sporthustle.hustle.friendmatch;

import com.sporthustle.hustle.common.annotation.UserId;
import com.sporthustle.hustle.friendmatch.dto.friendmatchingpost.CreateFriendMatchingPostRequestDTO;
import com.sporthustle.hustle.friendmatch.dto.friendmatchingpost.CreateFriendMatchingPostResponseDTO;
import com.sporthustle.hustle.friendmatch.dto.friendmatchingpost.FriendMatchingPostResponseDTO;
import com.sporthustle.hustle.friendmatch.dto.friendmatchingrequest.CreateFriendMatchingRequestRequestDTO;
import com.sporthustle.hustle.friendmatch.dto.friendmatchingrequest.CreateFriendMatchingRequestResponseDTO;
import com.sporthustle.hustle.friendmatch.dto.friendmatchingrequest.FriendMatchingRequestsResponseDTO;
import com.sporthustle.hustle.friendmatch.dto.friendmatchingrequest.UpdateFriendMatchingRequestStateRequestDTO;
import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPostType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "FriendMatching", description = "친선전 API")
@RestController
@RequestMapping("api/friendMatchingPosts")
@RequiredArgsConstructor
public class FriendMatchingController {
  private final FriendMatchingService friendMatchingService;

  @Operation(summary = "교류전 개설 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "교류전 개설에 성공한 경우"),
  })
  @PostMapping
  public ResponseEntity<CreateFriendMatchingPostResponseDTO> createFriendMatching(
      @UserId Long userId,
      @RequestBody CreateFriendMatchingPostRequestDTO createFriendMatchingPostRequestDTO) {
    CreateFriendMatchingPostResponseDTO createFriendMatchingPostResponseDTO =
        friendMatchingService.createFriendMatchingPost(userId, createFriendMatchingPostRequestDTO);
    return ResponseEntity.ok(createFriendMatchingPostResponseDTO);
  }

  @Operation(summary = "교류전 조회")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "교류전 조회에 성공한 경우"),
  })
  @GetMapping
  public ResponseEntity<Page<FriendMatchingPostResponseDTO>> getFriendMatchingPostsByTypeAndPage(
      @RequestParam(name = "sport_event_id", required = false) Long sportEventId,
      @RequestParam(name = "type", defaultValue = "INVITE") String type,
      @PageableDefault(size = 15, sort = "startDate", direction = Sort.Direction.DESC)
          Pageable pageable) {

    FriendMatchingPostType postType = FriendMatchingPostType.valueOf(type.toUpperCase());
    Page<FriendMatchingPostResponseDTO> friendMatchingPostsResponseDTO =
        friendMatchingService.getFriendMatchingPostsByType(sportEventId, postType, pageable);
    return ResponseEntity.ok(friendMatchingPostsResponseDTO);
  }

  /*
      @GetMapping("/{FriendMatchingPostId}")
      public ResponseEntity<FriendMatchingRequestsResponseDTO> getFriendMatchingRequests(@PathVariable("FriendMatchingPostId")Long friendMatchingPostId
              , @UserId Long userId){
          FriendMatchingRequestsResponseDTO friendMatchingRequestsResponseDTO = friendMatchingService.getFriendMatchingRequests(friendMatchingPostId,userId);
          return ResponseEntity.ok(friendMatchingRequestsResponseDTO);
      }
  */
  @Operation(summary = "교류전 지원 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "교류전 지원 생성에 성공한 경우"),
  })
  @PostMapping("/{friendMatchingPostId}")
  public ResponseEntity<CreateFriendMatchingRequestResponseDTO> applyFriendMatching(
      @PathVariable("friendMatchingPostId") Long friendMatchingPostId,
      @UserId Long userId,
      @RequestParam(name = "club_id", required = false) Long clubId,
      @RequestBody CreateFriendMatchingRequestRequestDTO createFriendMatchingRequestRequestDTO) {
    CreateFriendMatchingRequestResponseDTO createFriendMatchingRequestResponseDTO =
        friendMatchingService.applyFriendMatching(
            friendMatchingPostId, userId, clubId, createFriendMatchingRequestRequestDTO);
    return ResponseEntity.ok(createFriendMatchingRequestResponseDTO);
  }

  @Operation(summary = "교류전 지원 수락 거절 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "교류전 지원 (수락/거절) 상태 변경에 성공한 경우"),
  })
  @PutMapping("/{friendMatchingPostId}")
  public void updateFriendMatchingRequests(
      @PathVariable("friendMatchingPostId") Long friendMatchingPostId,
      @UserId Long userId,
      @RequestBody
          UpdateFriendMatchingRequestStateRequestDTO updateFriendMatchingRequestStateRequestDTO) {

    friendMatchingService.updateRequests(
        userId, friendMatchingPostId, updateFriendMatchingRequestStateRequestDTO);
  }

  @GetMapping("/{friendMatchingPostId}")
  public ResponseEntity<FriendMatchingRequestsResponseDTO> getFriendMatchingRequests(
      @PathVariable("friendMatchingPostId") Long friendMatchingPostId, @UserId Long userId) {
    return ResponseEntity.ok(friendMatchingService.getRequests(friendMatchingPostId, userId));
  }
}
