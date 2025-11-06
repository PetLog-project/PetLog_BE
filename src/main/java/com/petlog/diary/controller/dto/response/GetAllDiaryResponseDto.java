package com.petlog.diary.controller.dto.response;

import com.petlog.diary.service.dto.GetDailyDiaryDto;

import java.util.List;

public record GetAllDiaryResponseDto(

    List<GetDailyDiaryDto> allDiary

) {
    public GetAllDiaryResponseDto(final List<GetDailyDiaryDto> allDiary) {
        this.allDiary = allDiary;
    }
}
