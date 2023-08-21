package com.sporthustle.hustle.common.config.security;

import com.sporthustle.hustle.common.config.security.filter.JwtAuthenticationFilter;
import com.sporthustle.hustle.common.jwt.JwtTokenProvider;
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
    http.csrf().disable();
    http.httpBasic()
        .disable()
        .authorizeRequests()
        .antMatchers("/", "/api/auth/**", "/api/university", "/api/user/find/**")
        .permitAll()
        .antMatchers("/v2/api-docs")
        .permitAll()
        .antMatchers("/configuration/ui")
        .permitAll()
        .antMatchers("/swagger-resources/**")
        .permitAll()
        .antMatchers("/configuration/security")
        .permitAll()
        .antMatchers("/swagger-ui.html")
        .permitAll()
        .antMatchers("/swagger-ui/*")
        .permitAll()
        .antMatchers("/webjars/**")
        .permitAll()
        .antMatchers("/v2/**")
        .permitAll()
        .antMatchers("/api/**")
        .authenticated()
        .antMatchers("/api/admin/**")
        .hasRole("ADMIN")
        .antMatchers("/api/user/**")
        .hasRole("USER")
        .and()
        .addFilterBefore(
            new JwtAuthenticationFilter(jwtTokenProvider),
            UsernamePasswordAuthenticationFilter.class)
        .cors();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }
}
