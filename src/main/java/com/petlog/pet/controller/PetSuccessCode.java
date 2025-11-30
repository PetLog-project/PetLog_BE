package com.petlog.pet.controller;

import com.petlog.common.response.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PetSuccessCode implements SuccessCode {

    GET_PET_INFO(HttpStatus.OK.value(), "반려동물 정보 조회에 성공하였습니다."),
    UPDATE_PET_PROFILE(HttpStatus.OK.value(), "반려동물 정보 수정에 성공하였습니다."),
    CREATE_FEEDING_RECORD(HttpStatus.CREATED.value(), "밥 준 기록 등록에 성공하였습니다."),
    CREATE_WATERING_RECORD(HttpStatus.CREATED.value(), "물 준 기록 등록에 성공하였습니다."),
    CREATE_POOP_RECORD(HttpStatus.CREATED.value(), "배변 기록 등록에 성공하였습니다."),

    ;

    private final int value;
    private final String message;
}
