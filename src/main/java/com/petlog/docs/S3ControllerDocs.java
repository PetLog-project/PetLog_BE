package com.petlog.docs;

import com.petlog.auth.resolver.Authenticated;
import com.petlog.common.response.ApiResponse;
import com.petlog.s3.controller.dto.request.S3PresignedUrlsRequestDto;
import com.petlog.s3.controller.dto.response.S3PresignedUrlsResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "S3 API")
public interface S3ControllerDocs {

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "S3 presigned URL 발급에 성공하였습니다.")
    @Operation(summary = "S3 Presigned URL 발급 API")
    ResponseEntity<ApiResponse<S3PresignedUrlsResponseDto>> issueS3PresignedUrls(@Authenticated final Long memberId, @RequestBody final S3PresignedUrlsRequestDto request);
}
