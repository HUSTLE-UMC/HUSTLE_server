package com.sporthustle.hustle.user;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.user.entity.User;
import com.sporthustle.hustle.user.repository.UserRepository;

public class UserUtils {

  public static User getUserById(Long userId, UserRepository userRepository) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> BaseException.from(ErrorCode.USER_NOT_FOUND));
    return user;
  }

  public static User getUserByEmail(String email, UserRepository userRepository) {
    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> BaseException.from(ErrorCode.USER_NOT_FOUND));
    return user;
  }

  public static User getUserByName(String name, UserRepository userRepository) {
    User user =
        userRepository
            .findByName(name)
            .orElseThrow(() -> BaseException.from(ErrorCode.USER_NOT_FOUND));
    return user;
  }
}
