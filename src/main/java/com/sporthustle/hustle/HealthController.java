package com.sporthustle.hustle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Health", description = "헬스 체크 API")
@RequestMapping
@RestController
public class HealthController {

  @Operation(summary = "헬스 체크 API", description = "헬스 체크 API 입니다.")
  @GetMapping
  public ResponseEntity<String> health() {
    return ResponseEntity.ok(null);
  }
}
