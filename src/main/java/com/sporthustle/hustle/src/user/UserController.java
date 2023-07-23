package com.sporthustle.hustle.src.user;

import com.sporthustle.hustle.common.jwt.JwtTokenProvider;
import com.sporthustle.hustle.src.user.model.JoinReq;
import com.sporthustle.hustle.src.user.model.JoinRes;
import com.sporthustle.hustle.src.user.model.LoginReq;
import com.sporthustle.hustle.src.user.model.LoginRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
