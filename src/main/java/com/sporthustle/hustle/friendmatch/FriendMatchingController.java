package com.sporthustle.hustle.friendmatch;
import com.sporthustle.hustle.common.annotation.UserId;
import com.sporthustle.hustle.friendmatch.dto.*;
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

    @GetMapping("/{matchId}")
    public FriendMatchingResponseDTO getFriendMatchingRequests(@PathVariable("matchId")Long matchId
                                                              , @RequestParam (value = "userId") Long userId){
            return friendMatchingService.getRequests(matchId,userId);
    }

    @PutMapping("/{matchId}")
    public void updateFriendMatchingRequests(@PathVariable("matchId")Long matchId,
                                             @RequestBody UpdateStateRequestDTO updateStateRequestDTO){

        friendMatchingService.updateRequests(updateStateRequestDTO);

    }

    @PostMapping("/{matchId}")
    public ApplyResponseDTO applyFriendMatching(@PathVariable("matchId")Long matchId
                                                ,@RequestParam(value = "userId") Long userId
                                                ,@RequestBody ApplyRequestDTO applyRequestDTO){

        return friendMatchingService.applyFriendMatching(matchId, userId,applyRequestDTO);
    }

}

