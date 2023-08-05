package com.sporthustle.hustle.common.config.security;

import static com.sporthustle.hustle.common.exception.ErrorCode.SECURITY_CONTEXT_NOT_FOUND;

import com.sporthustle.hustle.common.exception.BaseException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

  public static String getCurrentUserEmail() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) throw new BaseException(SECURITY_CONTEXT_NOT_FOUND);

    if (authentication.isAuthenticated()) {
      return authentication.getName();
    }
    return null;
  }
}
