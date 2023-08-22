package com.sporthustle.hustle.auth;

import com.sporthustle.hustle.auth.dto.*;
import com.sporthustle.hustle.auth.dto.oauth.OAuthSignInRequestDTO;
import com.sporthustle.hustle.auth.dto.oauth.OAuthSignInResponseDTO;
import com.sporthustle.hustle.auth.dto.oauth.OAuthSignUpRequestDTO;
import com.sporthustle.hustle.auth.dto.oauth.OAuthSignUpResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "인증 API")
@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @Operation(summary = "회원가입 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "회원가입이 성공한 경우"),
    @ApiResponse(responseCode = "400", description = "이미 존재하는 유저인 경우")
  })
  @PostMapping("/signup")
  public ResponseEntity<SignUpResponseDTO> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
    SignUpResponseDTO signUpResponseDTO = authService.signUp(signUpRequestDTO);
    return ResponseEntity.ok(signUpResponseDTO);
  }

  @Operation(summary = "소셜 로그인 회원가입 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "회원가입이 성공한 경우"),
    @ApiResponse(responseCode = "400", description = "이미 존재하는 유저인 경우")
  })
  @PostMapping("/signup/oauth")
  public ResponseEntity<OAuthSignUpResponseDTO> oAuthSignUp(
      @RequestBody OAuthSignUpRequestDTO oAuthSignUpRequestDTO) {
    OAuthSignUpResponseDTO oAuthSignUpResponseDTO = authService.oAuthSignUp(oAuthSignUpRequestDTO);
    return ResponseEntity.ok(oAuthSignUpResponseDTO);
  }

  @Operation(summary = "로그인 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "인증이 성공한 경우"),
    @ApiResponse(responseCode = "400", description = "비밀번호가 올바르지 않은 경우")
  })
  @PostMapping("/signin")
  public ResponseEntity<SignInResponseDTO> signIn(@RequestBody SignInRequestDTO signInRequestDTO) {
    SignInResponseDTO signInResponseDTO = authService.signIn(signInRequestDTO);
    return ResponseEntity.ok(signInResponseDTO);
  }

  @Operation(summary = "소셜 로그인 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "인증이 성공한 경우"),
    @ApiResponse(responseCode = "400", description = "비밀번호가 올바르지 않은 경우")
  })
  @PostMapping("/signin/oauth")
  public ResponseEntity<OAuthSignInResponseDTO> oauthSignIn(
      @RequestBody OAuthSignInRequestDTO oAuthSignInRequestDTO) {
    OAuthSignInResponseDTO oAuthSignInResponseDTO = authService.oAuthSignIn(oAuthSignInRequestDTO);
    return ResponseEntity.ok(oAuthSignInResponseDTO);
  }

  @Operation(summary = "액세스 토큰 발급 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "발급이 성공한 경우"),
    @ApiResponse(responseCode = "400", description = "리프레쉬 토큰이 올바르지 않거나 만료된 경우")
  })
  @PostMapping("/refresh")
  public ResponseEntity<GetAccessTokenResponseDTO> getAccessTokenByRefreshToken(
      @RequestHeader("Authorization") String authorization) {
    GetAccessTokenResponseDTO getAccessTokenResponseDTO =
        authService.getAccessTokenByRefreshToken(authorization);
    return ResponseEntity.ok(getAccessTokenResponseDTO);
  }
}
