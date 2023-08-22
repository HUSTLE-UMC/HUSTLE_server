package com.sporthustle.hustle.infra.s3.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class S3UploadResponseDTO {

  private String message;

  private String data;
}
