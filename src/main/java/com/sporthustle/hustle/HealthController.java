package com.sporthustle.hustle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Tag(name = "Health", description = "헬스 체크 API")
@RequiredArgsConstructor
@RequestMapping
@RestController
public class HealthController {

  private final Environment env;

  @Operation(summary = "헬스 체크 API", description = "헬스 체크 API 입니다.")
  @GetMapping
  public ResponseEntity<String> health() {
    return ResponseEntity.ok(null);
  }

  @GetMapping("/profile")
  public String getProfile() {
    return Arrays.stream(env.getActiveProfiles()).findFirst().orElse("");
  }
}
