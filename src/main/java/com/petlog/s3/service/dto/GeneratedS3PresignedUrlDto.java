package com.petlog.s3.service.dto;

import java.util.List;

public record GeneratedS3PresignedUrlDto(

    List<S3PresignedUrlItem> items

) {
}
