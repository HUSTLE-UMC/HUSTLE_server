package com.sporthustle.hustle.friendmatch;
import com.sporthustle.hustle.common.annotation.UserId;
import com.sporthustle.hustle.friendmatch.dto.CreateFriendMatchingPostRequestDTO;
import com.sporthustle.hustle.friendmatch.dto.CreateFriendMatchingPostResponseDTO;
import com.sporthustle.hustle.friendmatch.dto.FriendMatchingPostResponseDTO;
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
@RequestMapping("api/friendMatching")
@RequiredArgsConstructor
public class FriendMatchingController {
    private final FriendMatchingService friendMatchingService;


    @Operation(summary = "교류전 개설 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "교류전 개설에 성공한 경우"),
    })
    @PostMapping
    public ResponseEntity<CreateFriendMatchingPostResponseDTO> createFriendMatching(
            @UserId Long userId, @RequestBody CreateFriendMatchingPostRequestDTO createFriendMatchingPostRequestDTO) {
        CreateFriendMatchingPostResponseDTO createFriendMatchingPostResponseDTO =
                friendMatchingService.createFriendMatchingPost(userId,createFriendMatchingPostRequestDTO);
        return ResponseEntity.ok(createFriendMatchingPostResponseDTO);
    }

    @Operation(summary = "교류전 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "교류전 조회에 성공한 경우"),
    })
    @GetMapping
    public ResponseEntity<Page<FriendMatchingPostResponseDTO>> getFriendMatchingPostsByTypeAndPage(
            @RequestParam(name = "sport_event_id" , required = false) Long sportEventId,
            @RequestParam(name = "type", defaultValue = "INVITE") String type,
            @PageableDefault(size = 15, sort = "startDate", direction = Sort.Direction.DESC) Pageable pageable) {

        FriendMatchingPostType postType = FriendMatchingPostType.valueOf(type.toUpperCase());
        Page<FriendMatchingPostResponseDTO> friendMatchingPostsResponseDTO = friendMatchingService.getFriendMatchingPostsByType(sportEventId, postType,pageable);
        return ResponseEntity.ok(friendMatchingPostsResponseDTO);
    }

}

