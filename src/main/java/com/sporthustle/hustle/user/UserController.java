package com.sporthustle.hustle.user;

import com.sporthustle.hustle.common.annotation.UserId;
import com.sporthustle.hustle.user.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "유저 API")
@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @Operation(summary = "내 정보 조회 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "내 정보를 성공적으로 조회하는 경우"),
  })
  @GetMapping
  public ResponseEntity<UserResponseDTO> getMyProfile(@UserId Long userId) {
    UserResponseDTO userResponseDTO = userService.getMyProfile(userId);
    return ResponseEntity.ok(userResponseDTO);
  }

  @Operation(summary = "내 정보 수정 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "내 정보를 성공적으로 수정한 경우"),
  })
  @PatchMapping
  public ResponseEntity<UpdateMyProfileResponseDTO> updateMyProfile(
      @UserId Long userId, @RequestBody UpdateMyProfileRequestDTO updateMyProfileRequestDTO) {
    UpdateMyProfileResponseDTO updateMyProfileResponseDTO =
        userService.updateMyProfile(userId, updateMyProfileRequestDTO);
    return ResponseEntity.ok(updateMyProfileResponseDTO);
  }

  @Operation(summary = "유저 탈퇴 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "성공적으로 탈퇴한 경우"),
  })
  @DeleteMapping
  public ResponseEntity<DeleteUserResponseDTO> deleteUser(@UserId Long userId) {
    DeleteUserResponseDTO deleteUserResponseDTO = userService.deleteUser(userId);
    return ResponseEntity.ok(deleteUserResponseDTO);
  }
}
