package com.petlog.common.exception;

import com.petlog.common.response.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public enum CommonErrorCode implements ErrorCode {
    INTERNAL_SERVER_APPLICATION("서버 애플리케이션에 예기치 못한 문제가 발생했습니다."),
    ;

    private final String value;
}
