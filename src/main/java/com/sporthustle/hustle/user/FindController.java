package com.sporthustle.hustle.user;

import com.sporthustle.hustle.user.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Find", description = "유저 계정 찾기 API")
@Slf4j
@RestController
@RequestMapping("/api/user/find")
@RequiredArgsConstructor
public class FindController {

  private final FindService findService;

  @Operation(summary = "아이디 찾기 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "이메일을 정상적으로 조회한 경우"),
    @ApiResponse(responseCode = "400", description = "'이름' 또는 '생년월일'이 일치하지 않는 경우")
  })
  @PostMapping("/email")
  public ResponseEntity<FindEmailResponseDTO> findEmail(
      @RequestBody FindEmailRequestDTO findEmailRequestDTO) {
    FindEmailResponseDTO findEmailResponseDTO = findService.findEmail(findEmailRequestDTO);
    return ResponseEntity.ok(findEmailResponseDTO);
  }

  @Operation(summary = "비밀번호 찾기 - 유저 유무 확인 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "정보에 해당하는 유저를 성공적으로 조회한 경우"),
    @ApiResponse(responseCode = "400", description = "'이메일' 또는 '이름', '생년월일'이 일치하지 않는 경우")
  })
  @PostMapping("/password")
  public ResponseEntity<FindPasswordResponseDTO> findPassword(
      @RequestBody FindPasswordRequestDTO findPasswordRequestDTO) {
    FindPasswordResponseDTO findPasswordResponseDTO =
        findService.findPassword(findPasswordRequestDTO);
    return ResponseEntity.ok(findPasswordResponseDTO);
  }

  @Operation(summary = "비밀번호 찾기 - 비밀번호 변경 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "비밀번호 변경에 성공한 경우"),
    @ApiResponse(responseCode = "400", description = "'이메일'에 해당하는 유저가 존재하지 않는 경우")
  })
  @PatchMapping("/password")
  public ResponseEntity<UpdatePasswordResponseDTO> updatePassword(
      @RequestBody UpdatePasswordRequestDTO updatePasswordRequestDTO) {
    UpdatePasswordResponseDTO updatePasswordResponseDTO =
        findService.updatePassword(updatePasswordRequestDTO);
    return ResponseEntity.ok(updatePasswordResponseDTO);
  }
}
