package com.petlog.s3.controller.dto.response;

import com.petlog.s3.service.dto.GeneratedS3PresignedUrlDto;

import java.util.List;

public record S3PresignedUrlsResponseDto(

    List<PresignedUrlResponseItem> presignedUrlItems

) {

    public static S3PresignedUrlsResponseDto from(final GeneratedS3PresignedUrlDto dto) {

        final List<PresignedUrlResponseItem> converted =
            dto.items().stream()
                .map(item -> new PresignedUrlResponseItem(
                    item.key(),
                    item.presignedUrl()
                ))
                .toList();

        return new S3PresignedUrlsResponseDto(converted);
    }

    public record PresignedUrlResponseItem(
        String filePath,
        String uploadUrl
    ) {}
}
