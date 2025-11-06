package com.petlog.diary.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetDiaryInfoDto(

    Long diaryId,

    @Schema(description = "10글자 제한")
    String title,

    String image

) {
    public GetDiaryInfoDto(
        final Long diaryId,
        final String title,
        final String image
    ) {
        this.diaryId = diaryId;
        this.title = title;
        this.image = image;
    }
}
