package com.sporthustle.hustle.friendmatch;

import com.sporthustle.hustle.common.annotation.UserId;
import com.sporthustle.hustle.friendmatch.dto.CreateFriendMatchingPostRequestDTO;
import com.sporthustle.hustle.friendmatch.dto.CreateFriendMatchingPostResponseDTO;
import com.sporthustle.hustle.friendmatch.dto.FriendMatchResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "FriendMatch", description = "친선전 API")
@RestController
@RequestMapping("api/friendMatch")
@RequiredArgsConstructor
public class FriendMatchingController {
    private final FriendMatchingService friendMatchingService;
}

