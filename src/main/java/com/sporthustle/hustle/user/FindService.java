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

    String email = user.getEmail();
    FindEmailResponseDTO findEmailResponseDTO = FindEmailResponseDTO.builder().email(email).build();

    return findEmailResponseDTO;
  }

  private void validateFindEmail(User user, FindEmailRequestDTO findEmailRequestDTO) {
    LocalDate birthday = findEmailRequestDTO.getBirthday();
    if (!user.getBirthday().equals(birthday)) {
      throw new BaseException(ErrorCode.USER_NOT_FOUND);
    }
  }

  @Transactional(readOnly = true)
  public FindPasswordResponseDTO findPassword(FindPasswordRequestDTO findPasswordRequestDTO) {
    String email = findPasswordRequestDTO.getEmail();
    User user = UserUtils.getUserByEmail(email, userRepository);

    validateFindPassword(user, findPasswordRequestDTO);

    FindPasswordResponseDTO findPasswordResponseDTO =
        FindPasswordResponseDTO.builder().result(true).build();

    return findPasswordResponseDTO;
  }

  private void validateFindPassword(User user, FindPasswordRequestDTO findPasswordRequestDTO) {
    String name = findPasswordRequestDTO.getName();
    LocalDate birthday = findPasswordRequestDTO.getBirthday();

    if (!user.getName().equals(name)) {
      throw new BaseException(ErrorCode.USER_NOT_FOUND);
    }

    if (!user.getBirthday().equals(birthday)) {
      throw new BaseException(ErrorCode.USER_NOT_FOUND);
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

    UpdatePasswordResponseDTO updatePasswordResponseDTO =
        UpdatePasswordResponseDTO.builder().result(true).build();
    return updatePasswordResponseDTO;
  }
}
