package com.sporthustle.hustle.src.user;

import static com.sporthustle.hustle.common.entity.BaseState.*;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.common.jwt.JwtTokenProvider;
import com.sporthustle.hustle.common.jwt.model.TokenInfo;
import com.sporthustle.hustle.src.university.UniversityRepository;
import com.sporthustle.hustle.src.university.entity.University;
import com.sporthustle.hustle.src.user.entity.Gender;
import com.sporthustle.hustle.src.user.entity.User;
import com.sporthustle.hustle.src.user.model.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

  private final JwtTokenProvider jwtTokenProvider;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final UniversityRepository universityRepository;

  public JoinRes join(JoinReq joinReq) {
    if (userRepository.existsByEmailAndState(joinReq.getEmail(), ACTIVE)) {
      throw new BaseException(ErrorCode.ALREADY_EXIST_USER);
    }
    University university =
        universityRepository
            .findById(joinReq.getUniversityId())
            .orElseThrow(() -> new BaseException(ErrorCode.UNIVERSITY_NOT_FOUND));
    User user =
        User.builder()
            .email(joinReq.getEmail())
            .password(passwordEncoder.encode(joinReq.getPassword()))
            .name(joinReq.getName())
            .birth(joinReq.getBirth())
            .gender(Gender.valueOf(joinReq.getGender()))
            .roles(List.of(joinReq.getRoles()))
            .university(university)
            .build();
    userRepository.save(user);
    return JoinRes.builder().message("회원가입 완료하였습니다.").build();
  }

  public LoginRes login(LoginReq loginReq) {
    User findUser =
        userRepository
            .findByEmailAndState(loginReq.getEmail(), ACTIVE)
            .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
    if (!passwordEncoder.matches(loginReq.getPassword(), findUser.getPassword())) {
      throw new BaseException(ErrorCode.INVALID_PASSWORD);
    }
    TokenInfo tokenInfo = jwtTokenProvider.createToken(loginReq.getEmail(), loginReq.getRoles());
    findUser.insertRefreshToken(tokenInfo.getRefreshToken());
    userRepository.save(findUser);
    return LoginRes.builder().tokenInfo(tokenInfo).build();
  }

  @Transactional(readOnly = true)
  public FindEmailRes findEmail(FindEmailReq findEmailReq) {
    User findUser =
        userRepository
            .findByNameAndBirthAndState(findEmailReq.getName(), findEmailReq.getBirth(), ACTIVE)
            .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
    return FindEmailRes.builder().email(findUser.getEmail()).build();
  }

  public ResetPasswordRes resetPassword(ResetPasswordReq resetPasswordReq) {
    User user =
        userRepository
            .findByEmailAndState(resetPasswordReq.getEmail(), ACTIVE)
            .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
    user.changePassword(passwordEncoder.encode(resetPasswordReq.getNewPassword()));
    return ResetPasswordRes.builder().message("비밀번호가 초기화 되었습니다.").build();
  }

  public boolean findAccount(FindAccountReq findAccountReq) {
    return userRepository.existsByEmailAndState(findAccountReq.getEmail(), ACTIVE);
  }
}
