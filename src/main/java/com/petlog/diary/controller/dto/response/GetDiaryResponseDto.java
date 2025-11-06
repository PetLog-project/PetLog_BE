package com.petlog.diary.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

public record GetDiaryResponseDto(

    @Schema(description = "10글자 제한")
    String title,

    @Schema(description = "3000글자 제한")
    String content,

    @Schema(description = "0~6장")
    List<String> images,

    LocalDate writtenAt,

    String writerName

) {
    public GetDiaryResponseDto(
        final String title,
        final String content,
        final List<String> images,
        final LocalDate writtenAt,
        final String writerName
    ) {
        this.title = title;
        this.content = content;
        this.images = images;
        this.writtenAt = writtenAt;
        this.writerName = writerName;
    }
}
