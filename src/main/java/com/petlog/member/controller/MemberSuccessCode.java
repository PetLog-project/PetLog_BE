package com.petlog.member.controller;

import com.petlog.common.response.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements SuccessCode {

    UPDATE_NOTIFICATION_WHETHER(HttpStatus.OK, "알림 여부 설정에 성공했습니다."),
    ;

    private final HttpStatus value;
    private final String message;
}
