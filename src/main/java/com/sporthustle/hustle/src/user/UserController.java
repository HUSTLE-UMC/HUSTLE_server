package com.sporthustle.hustle.src.user;

import com.sporthustle.hustle.common.jwt.JwtTokenProvider;
import com.sporthustle.hustle.src.user.model.*;
import io.swagger.v3.oas.annotations.Operation;
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

  private final JwtTokenProvider jwtTokenProvider;
  private final UserDetailService userDetailService;
  private final UserService userService;
  private final UserRepository userRepository;

  @Operation(summary = "회원가입 api")
  @PostMapping("/join")
  public ResponseEntity<JoinRes> join(@RequestBody JoinReq joinReq) {
    JoinRes userJoinRes = userService.join(joinReq);
    return ResponseEntity.ok(userJoinRes);
  }

  // 로그인
  @Operation(summary = "로그인 api")
  @PostMapping("/login")
  public ResponseEntity<LoginRes> login(@RequestBody LoginReq loginReq) {
    log.info("로그인 시도됨");
    LoginRes loginRes = userService.login(loginReq);
    return ResponseEntity.ok(loginRes);
  }

  @Operation(summary = "아이디 찾기 api")
  @GetMapping("/find/email")
  public ResponseEntity<FindEmailRes> findEmail(@RequestBody FindEmailReq findEmailReq) {
    FindEmailRes findEmailRes = userService.findEmail(findEmailReq);
    return ResponseEntity.ok(findEmailRes);
  }

  @Operation(summary = "비밀번호 초기화를 위한 계정 찾기 api")
  @GetMapping("/find/account")
  public ResponseEntity<FindAccountRes> findAccount(@RequestBody FindAccountReq findAccountReq) {
    String result = "존재하지 않는 계정입니다.";
    if (userService.findAccount(findAccountReq)) {
      result = "비밀번호 변경가능합니다.";
    }
    return ResponseEntity.ok(FindAccountRes.builder().message(result).build());
  }

  @Operation(summary = "비밀번호 초기화 api")
  @PatchMapping("/reset/password")
  public ResponseEntity<ResetPasswordRes> resetPassword(
      @RequestBody ResetPasswordReq resetPasswordReq) {
    ResetPasswordRes resetPasswordRes = userService.resetPassword(resetPasswordReq);
    return ResponseEntity.ok(resetPasswordRes);
  }
}
