package com.petlog.s3.controller;

import com.petlog.auth.resolver.Authenticated;
import com.petlog.common.response.ApiResponse;
import com.petlog.docs.S3ControllerDocs;
import com.petlog.s3.controller.dto.request.S3PresignedUrlsRequestDto;
import com.petlog.s3.controller.dto.response.S3PresignedUrlsResponseDto;
import com.petlog.s3.service.S3Service;
import com.petlog.s3.service.dto.GeneratedS3PresignedUrlDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.petlog.s3.controller.S3SuccessCode.GENERATE_S3_PRESIGNED_URLS;

@RequiredArgsConstructor
@RequestMapping("/api/s3")
@RestController
public class S3Controller implements S3ControllerDocs {

    private final S3Service s3Service;

    @PostMapping("/presigned-urls")
    public ResponseEntity<ApiResponse<S3PresignedUrlsResponseDto>> issueS3PresignedUrls(
        @Authenticated final Long memberId,
        @RequestBody final S3PresignedUrlsRequestDto request
    ) {
        final GeneratedS3PresignedUrlDto generatedS3PresignedUrl = s3Service.generateS3PresignedUrl(memberId, request.fileType(), request.fileNames());
        final S3PresignedUrlsResponseDto response = S3PresignedUrlsResponseDto.from(generatedS3PresignedUrl);

        return ResponseEntity.ok(
            ApiResponse.successWithData(GENERATE_S3_PRESIGNED_URLS, response)
        );
    }
}
