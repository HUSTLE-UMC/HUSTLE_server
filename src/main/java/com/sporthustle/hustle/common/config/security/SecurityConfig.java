package com.sporthustle.hustle.common.config.security;

import com.sporthustle.hustle.common.config.security.filter.JwtAuthenticationFilter;
import com.sporthustle.hustle.common.consts.Constants;
import com.sporthustle.hustle.common.jwt.JwtTokenProvider;
import com.sporthustle.hustle.user.entity.UserRole;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtTokenProvider jwtTokenProvider;

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public BCryptPasswordEncoder encodePassword() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf() // Rest API 이므로 CSRF 토큰 인증 비활성화
        .disable()
        .httpBasic() // HTTP Request 헤더에서 평문으로 계정 인증하는 기본 인증 비활성화
        .disable()
        .cors()
        .configurationSource(corsConfigurationSource())
        .and()
        .authorizeRequests()
        .antMatchers(getPermitAllPatterns())
        .permitAll()
        .antMatchers(getAuthenticatedPatterns())
        .authenticated()
        .antMatchers(getAdminRolePatterns())
        .hasRole(UserRole.ADMIN.name())
        .and()
        .addFilterBefore(
            new JwtAuthenticationFilter(jwtTokenProvider),
            UsernamePasswordAuthenticationFilter.class);
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  private String[] getPermitAllPatterns() {
    List<String> permitAllPatterns =
        List.of(
            "/",
            "/api/auth/**",
            "/api/university",
            "/api/user/find/**",
            "/swagger-ui/**",
            "/api/oauth/**",
            "/api/s3/**");
    return permitAllPatterns.toArray(new String[0]);
  }

  private String[] getAuthenticatedPatterns() {
    List<String> authenticatedPatterns = List.of("/api/**");
    return authenticatedPatterns.toArray(new String[0]);
  }

  private String[] getAdminRolePatterns() {
    List<String> adminRolePatterns = List.of("/api/admin/**");
    return adminRolePatterns.toArray(new String[0]);
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Constants.ALLOWED_DOMAIN_ORIGINS);
    configuration.setAllowedMethods(Arrays.asList(Constants.PERMIT_ALL));
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
