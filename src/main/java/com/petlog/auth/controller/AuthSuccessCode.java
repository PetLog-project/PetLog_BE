package com.petlog.auth.controller;

import com.petlog.common.response.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthSuccessCode implements SuccessCode {

    GENERATE_TOKEN(HttpStatus.OK, "JWT token 발급에 성공하였습니다."),
    TOKEN_REFRESH(HttpStatus.CREATED, "JWT access token 재발급에 성공하였습니다."),
    ;

    private final HttpStatus value;
    private final String message;
}
