package com.petlog.common.exception;

import com.petlog.common.response.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public enum CommonErrorCode implements ErrorCode {
    INTERNAL_SERVER_APPLICATION(HttpStatus.INTERNAL_SERVER_ERROR.value()),
    ;

    private final int value;
}
