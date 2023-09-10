package com.sporthustle.hustle.oauth;

import com.sporthustle.hustle.oauth.dto.GetKakaoTokenResponseDTO;
import com.sporthustle.hustle.oauth.dto.GetKakaoUserInformationResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "OAuth", description = "소셜 로그인 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class OAuthController {

  private final OAuthService oAuthService;

  @GetMapping("/kakao/token")
  public ResponseEntity<GetKakaoTokenResponseDTO> getKakaoToken(@RequestParam("code") String code) {
    return ResponseEntity.ok(oAuthService.getKakaoToken(code));
  }

  @GetMapping("/kakao/information")
  public ResponseEntity<GetKakaoUserInformationResponseDTO> getKakaoUserInformation(
      @RequestParam("token") String token) {
    return ResponseEntity.ok(oAuthService.getKakaoUserInformation(token));
  }
}
