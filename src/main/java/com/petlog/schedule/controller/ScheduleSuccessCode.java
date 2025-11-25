package com.petlog.schedule.controller;

import com.petlog.common.response.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ScheduleSuccessCode implements SuccessCode {

    CREATE_SCHEDULE(HttpStatus.CREATED, "일정 생성에 성공하였습니다."),
    GET_ALL_SCHEDULE(HttpStatus.OK, "월별 일정 전체 조회에 성공하였습니다."),
    UPDATE_SCHEDULE(HttpStatus.OK, "일정 상세 내용 수정에 성공하였습니다."),
    DELETE_SCHEDULE(HttpStatus.OK, "일정 삭제에 성공하였습니다."),
    ;

    private final HttpStatus value;
    private final String message;
}
