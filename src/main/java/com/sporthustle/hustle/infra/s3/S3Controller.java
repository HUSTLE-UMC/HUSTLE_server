package com.sporthustle.hustle.infra.s3;

import com.sporthustle.hustle.infra.s3.dto.S3UploadResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "S3 File Upload", description = "s3 파일 업로드 API")
@Slf4j
@RestController
@RequestMapping("/api/s3")
@RequiredArgsConstructor
public class S3Controller {

  private final S3Service s3Service;

  @Operation(summary = "파일 업로드 API")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "파일 업로드에 성공한 경우"),
  })
  @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<S3UploadResponseDTO> uploadFile(@RequestPart("file") MultipartFile file) {
    S3UploadResponseDTO s3UploadResponseDTO = s3Service.uploadFile(file);
    return ResponseEntity.ok(s3UploadResponseDTO);
  }
}
