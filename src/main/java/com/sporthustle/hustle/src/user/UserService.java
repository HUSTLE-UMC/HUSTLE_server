package com.sporthustle.hustle.src.user;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.common.jwt.JwtTokenProvider;
import com.sporthustle.hustle.common.jwt.model.TokenInfo;
import com.sporthustle.hustle.src.user.entity.Gender;
import com.sporthustle.hustle.src.user.entity.User;
import com.sporthustle.hustle.src.user.model.JoinReq;
import com.sporthustle.hustle.src.user.model.JoinRes;
import com.sporthustle.hustle.src.user.model.LoginReq;
import com.sporthustle.hustle.src.user.model.LoginRes;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final JwtTokenProvider jwtTokenProvider;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  public JoinRes join(JoinReq joinReq) {
    if (userRepository.existsByEmail(joinReq.getEmail())) {
      throw new BaseException(ErrorCode.ALREADY_EXIST_USER);
    }
    User user =
        User.builder()
            .email(joinReq.getEmail())
            .password(passwordEncoder.encode(joinReq.getPassword()))
            .birth(joinReq.getBirth())
            .nickname(joinReq.getNickname())
            .gender(Gender.valueOf(joinReq.getGender()))
            .roles(List.of(joinReq.getRoles()))
            .build();
    userRepository.save(user);
    return JoinRes.builder().message("회원가입 완료하였습니다.").build();
  }

  public LoginRes login(LoginReq loginReq) {
    User findUser =
        userRepository
            .findByEmail(loginReq.getEmail())
            .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
    if (!passwordEncoder.matches(loginReq.getPassword(), findUser.getPassword())) {
      throw new BaseException(ErrorCode.INVALID_PASSWORD);
    }
    TokenInfo tokenInfo = jwtTokenProvider.createToken(loginReq.getEmail(), loginReq.getRoles());
    findUser.insertRefreshToken(tokenInfo.getRefreshToken());
    userRepository.save(findUser);
    return LoginRes.builder().tokenInfo(tokenInfo).build();
  }
}
