package com.petlog.diary.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record GetDiaryInfoDto(

    Long diaryId,

    @Schema(description = "10글자 제한")
    String title,

    String image,

    LocalDate writtenAt

) {
}
