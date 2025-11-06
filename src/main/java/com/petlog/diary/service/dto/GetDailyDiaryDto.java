package com.petlog.diary.service.dto;

import java.time.LocalDate;
import java.util.List;

public record GetDailyDiaryDto(

    LocalDate writtenAt,
    List<GetDiaryInfoDto> diaryInfo

) {
    public GetDailyDiaryDto(
        final LocalDate writtenAt,
        final List<GetDiaryInfoDto> diaryInfo
    ) {
        this.writtenAt = writtenAt;
        this.diaryInfo = diaryInfo;
    }
}
