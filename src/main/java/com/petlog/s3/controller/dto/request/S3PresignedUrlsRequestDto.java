package com.petlog.s3.controller.dto.request;

import java.util.List;

public record S3PresignedUrlsRequestDto(

    Long diaryId,
    List<String> fileNames

) {
}
