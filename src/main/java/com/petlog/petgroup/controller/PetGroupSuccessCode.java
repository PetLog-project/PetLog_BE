package com.petlog.petgroup.controller;

import com.petlog.common.response.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PetGroupSuccessCode implements SuccessCode {

    CREATE_PET_GROUP(HttpStatus.CREATED, "그룹 생성에 성공하였습니다."),
    JOIN_PET_GROUP(HttpStatus.OK, "그룹 참여에 성공하였습니다."),
    LEAVE_PET_GROUP(HttpStatus.OK, "그룹 탈퇴에 성공하였습니다."),
    GET_JOIN_CODE(HttpStatus.OK, "초대코드 조회에 성공하였습니다."),
    GET_NOTE(HttpStatus.OK, "참고사항 조회에 성공하였습니다."),
    UPDATE_NOTE(HttpStatus.OK, "참고사항 수정에 성공하였습니다."),
    ;

    private final HttpStatus value;
    private final String message;
}
