package com.sporthustle.hustle.src.user;

import com.sporthustle.hustle.common.jwt.JwtTokenProvider;
import com.sporthustle.hustle.src.user.model.JoinReq;
import com.sporthustle.hustle.src.user.model.JoinRes;
import com.sporthustle.hustle.src.user.model.LoginReq;
import com.sporthustle.hustle.src.user.model.LoginRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final JwtTokenProvider jwtTokenProvider;
  private final UserDetailService userDetailService;
  private final UserService userService;
  private final UserRepository userRepository;

  @PostMapping("/join")
  public ResponseEntity<JoinRes> join(@RequestBody JoinReq joinReq) {
    log.info("로그인 시도됨");
    JoinRes userJoinRes = userService.join(joinReq);
    return ResponseEntity.ok(userJoinRes);
  }

  // 로그인
  @PostMapping("/login")
  public ResponseEntity<LoginRes> login(@RequestBody LoginReq loginReq) {
    LoginRes loginRes = userService.login(loginReq);
    return ResponseEntity.ok(loginRes);
  }
}
