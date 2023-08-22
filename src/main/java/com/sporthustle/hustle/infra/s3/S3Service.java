package com.sporthustle.hustle.infra.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.infra.s3.dto.S3UploadResponseDTO;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class S3Service {

  private final AmazonS3Client amazonS3Client;

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  @Value("${cloud.aws.s3.bucket_url}")
  private String bucketUrl;

  public S3UploadResponseDTO uploadFile(MultipartFile file) {
    String generatedFileName = buildFileName(file.getOriginalFilename());

    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentType(file.getContentType());
    metadata.setContentLength(file.getSize());

    validateImageFile(file);

    try (InputStream inputStream = file.getInputStream()) {
      amazonS3Client.putObject(bucket, generatedFileName, inputStream, metadata);
    } catch (IOException e) {
      throw BaseException.from(ErrorCode.FILE_UPLOAD_FAILED);
    }

    String fileUrl = bucketUrl + "/" + generatedFileName;
    return S3UploadResponseDTO.builder().message("업로드 완료되었습니다.").data(fileUrl).build();
  }

  private String buildFileName(String originalFileName) {
    if (originalFileName.indexOf('.') == -1) {
      throw new IllegalArgumentException("파일 확장자가 존재하지 않는 파일입니다.");
    }

    String[] fileSplitByDot = originalFileName.split("\\.");
    String fileExtension = fileSplitByDot[fileSplitByDot.length - 1];
    return UUID.randomUUID() + "." + fileExtension;
  }

  private void validateImageFile(MultipartFile file) {
    if (!file.getContentType().startsWith("image")) {
      throw BaseException.from(ErrorCode.FILE_NOT_IMAGE);
    }
  }
}
