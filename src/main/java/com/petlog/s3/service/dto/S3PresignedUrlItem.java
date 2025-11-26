package com.petlog.s3.service.dto;

public record S3PresignedUrlItem(

    String key,
    String presignedUrl

) {
}
