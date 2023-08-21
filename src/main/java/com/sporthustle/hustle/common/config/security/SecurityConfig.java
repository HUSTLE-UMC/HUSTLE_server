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
    http.csrf()
        .disable()
        .httpBasic()
        .disable()
        .cors()
        .configurationSource(corsConfigurationSource())
        .and()
        .authorizeRequests()
        .antMatchers(getPermitAllPatterns().toArray(new String[0]))
        .permitAll()
        .antMatchers(getAuthenticatedPatterns().toArray(new String[0]))
        .authenticated()
        .antMatchers(getAdminRolePatterns().toArray(new String[0]))
        .hasRole(UserRole.ADMIN.name())
        .and()
        .addFilterBefore(
            new JwtAuthenticationFilter(jwtTokenProvider),
            UsernamePasswordAuthenticationFilter.class);
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  private List<String> getPermitAllPatterns() {
    return List.of("/", "/api/auth/**", "/api/university", "/api/user/find/**", "/swagger-ui/**");
  }

  private List<String> getAuthenticatedPatterns() {
    return List.of("/api/**");
  }

  private List<String> getAdminRolePatterns() {
    return List.of("/api/admin/**");
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
