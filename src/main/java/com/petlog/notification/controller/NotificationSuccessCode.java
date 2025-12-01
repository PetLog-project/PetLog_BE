package com.petlog.notification.controller;

import com.petlog.common.response.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum NotificationSuccessCode implements SuccessCode {

    SAVE_NOTIFICATION_TOKEN(HttpStatus.CREATED.value(), "푸시 알림 토큰 저장에 성공하였습니다."),
    DELETE_NOTIFICATION_TOKEN(HttpStatus.OK.value(), "푸시 알림 토큰 삭제에 성공하였습니다."),
    ;

    private final int value;
    private final String message;
}
