package com.sporthustle.hustle.common.resolver;

import com.sporthustle.hustle.common.annotation.UserId;
import com.sporthustle.hustle.user.entity.User;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthUserResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(UserId.class)
        && parameter.getParameterType().equals(Long.class);
  }

  @Override
  public Object resolveArgument(
      MethodParameter parameter,
      ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory)
      throws Exception {

    if (!supportsParameter(parameter)) {
      return WebArgumentResolver.UNRESOLVED;
    }

    Authentication authentication = (Authentication) webRequest.getUserPrincipal();
    User user = (User) authentication.getPrincipal();
    Long userId = user.getId();

    return userId;
  }
}
