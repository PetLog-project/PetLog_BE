package com.petlog.member.controller;

import com.petlog.common.response.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements SuccessCode {

    GET_IS_NOTIFICATION_ENABLED(HttpStatus.OK, "알림 수신 여부 조회에 성공하였습니다."),
    UPDATE_IS_NOTIFICATION_ENABLED(HttpStatus.OK, "알림 수신 여부 설정에 성공하였습니다."),
    WITHDRAW(HttpStatus.OK, "회원 탈퇴에 성공하였습니다."),
    ;

    private final HttpStatus value;
    private final String message;
}
