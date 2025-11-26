package com.petlog.s3.service;

import com.petlog.s3.generator.PresignedUrlGenerator;
import com.petlog.s3.service.dto.GeneratedS3PresignedUrlDto;
import com.petlog.s3.service.dto.S3PresignedUrlItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3Service {

    private static final String DIARY_IMAGE_ROOT_DIRECTORY = "diary_image";

    private final PresignedUrlGenerator presignedUrlGenerator;

    @Transactional(readOnly = true)
    public GeneratedS3PresignedUrlDto generateS3PresignedUrl(final Long diaryId, final List<String> fileNames) {
        final List<String> keys = keys(diaryId, fileNames);
        final List<S3PresignedUrlItem> items = presignedUrlGenerator.generate(keys);

        return new GeneratedS3PresignedUrlDto(items);
    }

    private List<String> keys(final Long diaryId, final List<String> fileNames) {
        return fileNames.stream()
            .map(fileName -> "%s/%d/%s/%s".formatted(
                DIARY_IMAGE_ROOT_DIRECTORY,
                diaryId,
                UUID.randomUUID(),
                fileName
            ))
            .toList();
    }
}
