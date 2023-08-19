package com.sporthustle.hustle.friendmatch;
import com.sporthustle.hustle.common.annotation.UserId;
import com.sporthustle.hustle.friendmatch.dto.CreateFriendMatchingPostRequestDTO;
import com.sporthustle.hustle.friendmatch.dto.CreateFriendMatchingPostResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
            @ApiResponse(responseCode = "200", description = "대회 수정에 성공한 경우"),
    })
    @PostMapping
    public ResponseEntity<CreateFriendMatchingPostResponseDTO> createFriendMatching(
            @UserId Long userId, @RequestBody CreateFriendMatchingPostRequestDTO createFriendMatchingPostRequestDTO) {
        CreateFriendMatchingPostResponseDTO createFriendMatchingPostResponseDTO =
                friendMatchingService.createFriendMatchingPost(userId,createFriendMatchingPostRequestDTO);
        return ResponseEntity.ok(createFriendMatchingPostResponseDTO);
    }



}

