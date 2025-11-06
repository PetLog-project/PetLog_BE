package com.petlog.diary.controller;

import com.petlog.common.response.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum DiarySuccessCode implements SuccessCode {

    GET_ALL_DIARY(HttpStatus.OK, "일기 전체 조회에 성공하였습니다."),
    GET_DIARY(HttpStatus.OK, "일기 상세 조회에 성공하였습니다."),
    UPDATE_DIARY(HttpStatus.OK, "일기 상세 내용 수정에 성공하였습니다."),
    DELETE_DIARY(HttpStatus.OK, "일기 삭제가 성공하였습니다."),
    ;

    private final HttpStatus value;
    private final String message;
}
