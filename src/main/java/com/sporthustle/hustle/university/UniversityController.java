package com.sporthustle.hustle.university;

import com.sporthustle.hustle.university.dto.FindUniversitiesResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "University", description = "대학 API")
@Slf4j
@RestController
@RequestMapping("/api/university")
@RequiredArgsConstructor
public class UniversityController {

  private final UniversityService universityService;

  @Operation(summary = "대학 목록 조회 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "대학 목록을 정상적으로 조회한 경우"),
  })
  @GetMapping
  public ResponseEntity<FindUniversitiesResponseDTO> findUniversities(
      @RequestParam(value = "keyword") String keyword) {
    FindUniversitiesResponseDTO findUniversitiesResponseDTO =
        universityService.findUniversities(keyword);
    return ResponseEntity.ok(findUniversitiesResponseDTO);
  }
}
