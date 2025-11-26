package com.petlog.s3.generator;

import com.petlog.s3.service.dto.S3PresignedUrlItem;

import java.util.List;

public interface PresignedUrlGenerator {

    List<S3PresignedUrlItem> generate(List<String> keys);
}
