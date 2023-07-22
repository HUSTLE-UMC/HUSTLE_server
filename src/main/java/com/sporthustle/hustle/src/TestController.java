package com.sporthustle.hustle.src;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @PostMapping("/test")
  public ResponseEntity<String> test() {
    String result = "로그인 로직 통과";
    return ResponseEntity.ok(result);
  }
}
