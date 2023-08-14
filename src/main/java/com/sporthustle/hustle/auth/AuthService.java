package com.sporthustle.hustle.auth;

import static com.sporthustle.hustle.common.consts.Constants.REFRESH_TOKEN;

import com.sporthustle.hustle.auth.dto.*;
import com.sporthustle.hustle.common.entity.BaseStatus;
import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.common.jwt.JwtTokenProvider;
import com.sporthustle.hustle.common.jwt.dto.TokenInfo;
import com.sporthustle.hustle.university.UniversityUtils;
import com.sporthustle.hustle.university.entity.University;
import com.sporthustle.hustle.university.repository.UniversityRepository;
import com.sporthustle.hustle.user.entity.Gender;
import com.sporthustle.hustle.user.entity.SnsType;
import com.sporthustle.hustle.user.entity.User;
import com.sporthustle.hustle.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

  private final JwtTokenProvider jwtTokenProvider;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final UniversityRepository universityRepository;

  @Transactional
  public SignUpResponseDTO signUp(SignUpRequestDTO signUpRequestDTO) {

    validateSignUp(signUpRequestDTO);

    String hashedPassword = passwordEncoder.encode(signUpRequestDTO.getPassword());
    User user =
        User.builder()
            .email(signUpRequestDTO.getEmail())
            .password(hashedPassword)
            .birthday(signUpRequestDTO.getBirthday())
            .name(signUpRequestDTO.getName())
            .gender(Gender.valueOf(signUpRequestDTO.getGender()))
            .isMailing(signUpRequestDTO.getIsMailing())
            .build();

    String snsId = signUpRequestDTO.getSnsId();
    SnsType snsType = signUpRequestDTO.getSnsType();
    user.updateSnsValue(snsId, snsType);

    Long universityId = signUpRequestDTO.getUniversityId();
    University university = UniversityUtils.getUniversityById(universityId, universityRepository);
    user.updateUniversity(university);

    userRepository.save(user);

    SignUpResponseDTO signUpResponseDTO =
        SignUpResponseDTO.builder().message("회원가입 완료하였습니다.").build();

    return signUpResponseDTO;
  }

  private void validateSignUp(SignUpRequestDTO signUpRequestDTO) {
    boolean isExistEmail = userRepository.existsByEmail(signUpRequestDTO.getEmail());
    if (isExistEmail) {
      throw BaseException.from(ErrorCode.ALREADY_EXIST_USER);
    }
  }

  @Transactional
  public SignInResponseDTO signIn(SignInRequestDTO signInRequestDTO) {
    String email = signInRequestDTO.getEmail();

    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

    validateDeletedUser(user.getStatus());

    boolean isMatchedPassword =
        passwordEncoder.matches(signInRequestDTO.getPassword(), user.getPassword());
    if (!isMatchedPassword) {
      throw BaseException.from(ErrorCode.INVALID_PASSWORD);
    }

    TokenInfo tokenInfo = jwtTokenProvider.createToken(user.getEmail());
    user.insertRefreshToken(tokenInfo.getRefreshToken());
    userRepository.save(user);

    SignInResponseDTO signInResponseDTO = SignInResponseDTO.builder().tokenInfo(tokenInfo).build();

    return signInResponseDTO;
  }

  private void validateDeletedUser(BaseStatus status) {
    if (status == BaseStatus.INACTIVE) {
      throw BaseException.from(ErrorCode.USER_NOT_FOUND);
    }
  }

  @Transactional(readOnly = true)
  public GetAccessTokenResponseDTO getAccessTokenByRefreshToken(String authorization) {
    String refreshToken = jwtTokenProvider.refineToken(authorization);

    validateJwtRefreshToken(refreshToken);

    User user =
        userRepository
            .findByRefreshToken(refreshToken)
            .orElseThrow(() -> BaseException.from(ErrorCode.REFRESH_TOKEN_NOT_FOUND));

    String email = user.getEmail();
    TokenInfo newTokenInfo = jwtTokenProvider.createToken(user.getEmail());
    TokenInfo originalRefreshTokenInfo =
        TokenInfo.builder()
            .accessToken(newTokenInfo.getAccessToken())
            .refreshToken(refreshToken)
            .build();

    return GetAccessTokenResponseDTO.builder().tokenInfo(originalRefreshTokenInfo).build();
  }

  private void validateJwtRefreshToken(String refreshToken) {
    if (refreshToken == null) {
      throw BaseException.from(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }

    if (!jwtTokenProvider.validateToken(refreshToken)) {
      throw BaseException.from(ErrorCode.REFRESH_TOKEN_EXPIRED);
    }

    String tokenType = jwtTokenProvider.getTokenType(refreshToken);
    if (!tokenType.equals(REFRESH_TOKEN)) {
      throw BaseException.from(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
  }
}
