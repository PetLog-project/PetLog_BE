package com.petlog.s3.controller;

import com.petlog.common.response.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum S3SuccessCode implements SuccessCode {

    GENERATE_S3_PRESIGNED_URLS(HttpStatus.CREATED.value(), "S3 presigned URL 발급에 성공하였습니다."),
    ;

    private final int value;
    private final String message;
}
