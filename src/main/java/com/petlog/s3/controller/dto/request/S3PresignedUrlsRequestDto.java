package com.petlog.s3.controller.dto.request;

import com.petlog.s3.service.FileType;

import java.util.List;

public record S3PresignedUrlsRequestDto(

    FileType fileType,
    List<String> fileNames

) {
}
