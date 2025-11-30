package com.petlog.diary.service.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public record GetDailyDiaryDto(

    @DateTimeFormat(pattern = "yyyy.MM.dd")
    LocalDate writtenAt,
    List<GetDiaryInfoDto> diaryInfo

) {
}
