package com.sporthustle.hustle.infra.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.infra.s3.dto.S3UploadResponseDTO;
import java.io.IOException;
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

  public S3UploadResponseDTO uploadFile(MultipartFile file) {
    try {
      String fileName = file.getOriginalFilename();
      String fileUrl = "https://" + bucket + "/test" + fileName;
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentType(file.getContentType());
      metadata.setContentLength(file.getSize());
      amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);
      return S3UploadResponseDTO.builder().message("업로드 완료되었습니다.").build();
    } catch (IOException e) {
      e.printStackTrace();
      throw BaseException.from(ErrorCode.USER_NOT_FOUND);
    }
  }
}
