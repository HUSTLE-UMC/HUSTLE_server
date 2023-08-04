package com.sporthustle.hustle.src;

import com.sporthustle.hustle.common.config.security.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Test", description = "테스트 API")
@RestController
public class TestController {

  @Operation(summary = "테스트 api", description = "테스트 api 입니다.")
  @PostMapping("/api/test")
  public ResponseEntity<String> test() {
    String result = SecurityUtils.getCurrentUserEmail();
    return ResponseEntity.ok(result);
  }
}
