package com.sporthustle.hustle.infra.s3;

import com.sporthustle.hustle.infra.s3.dto.S3UploadResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "S3 File Upload", description = "s3 파일 업로드 API")
@Slf4j
@RestController
@RequestMapping("/api/s3")
@RequiredArgsConstructor
public class S3Controller {

  private final S3Service s3Service;

  @PostMapping("/upload")
  public ResponseEntity<S3UploadResponseDTO> uploadFile(@RequestParam("file") MultipartFile file) {
    S3UploadResponseDTO s3UploadResponseDTO = s3Service.uploadFile(file);
    return ResponseEntity.ok(s3UploadResponseDTO);
  }
}
