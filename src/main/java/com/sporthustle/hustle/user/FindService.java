package com.sporthustle.hustle.user;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.user.dto.*;
import com.sporthustle.hustle.user.entity.User;
import com.sporthustle.hustle.user.repository.UserRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FindService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional(readOnly = true)
  public FindEmailResponseDTO findEmail(FindEmailRequestDTO findEmailRequestDTO) {
    String name = findEmailRequestDTO.getName();
    User user = UserUtils.getUserByName(name, userRepository);

    validateFindEmail(user, findEmailRequestDTO);

    return FindEmailResponseDTO.builder()
        .code("SUCCESS_FIND_EMAIL")
        .message("이메일 찾기를 성공했습니다.")
        .data(user.getEmail())
        .build();
  }

  private void validateFindEmail(User user, FindEmailRequestDTO findEmailRequestDTO) {
    LocalDate birthday = findEmailRequestDTO.getBirthday();
    if (!user.getBirthday().equals(birthday)) {
      throw BaseException.from(ErrorCode.USER_NOT_FOUND);
    }
  }

  @Transactional(readOnly = true)
  public FindPasswordResponseDTO findPassword(FindPasswordRequestDTO findPasswordRequestDTO) {
    String email = findPasswordRequestDTO.getEmail();
    User user = UserUtils.getUserByEmail(email, userRepository);

    validateFindPassword(user, findPasswordRequestDTO);

    return FindPasswordResponseDTO.builder()
        .code("SUCCESS_CHANGEABLE_PASSWORD")
        .message("비밀번호를 변경할 수 있습니다.")
        .data(true)
        .build();
  }

  private void validateFindPassword(User user, FindPasswordRequestDTO findPasswordRequestDTO) {
    String name = findPasswordRequestDTO.getName();
    LocalDate birthday = findPasswordRequestDTO.getBirthday();

    if (!user.getName().equals(name)) {
      throw BaseException.from(ErrorCode.USER_NOT_FOUND);
    }

    if (!user.getBirthday().equals(birthday)) {
      throw BaseException.from(ErrorCode.USER_NOT_FOUND);
    }
  }

  @Transactional
  public UpdatePasswordResponseDTO updatePassword(
      UpdatePasswordRequestDTO updatePasswordRequestDTO) {
    String email = updatePasswordRequestDTO.getEmail();
    User user = UserUtils.getUserByEmail(email, userRepository);

    String hashedPassword = passwordEncoder.encode(updatePasswordRequestDTO.getNewPassword());
    user.updateNewPassword(hashedPassword);
    userRepository.save(user);

    return UpdatePasswordResponseDTO.builder()
        .code("SUCCESS_UPDATE_PASSWORD")
        .message("성공적으로 비밀번호를 변경했습니다.")
        .data(true)
        .build();
  }
}
