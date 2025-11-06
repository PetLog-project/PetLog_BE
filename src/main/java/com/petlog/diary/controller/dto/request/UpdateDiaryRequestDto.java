package com.petlog.diary.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

public record UpdateDiaryRequestDto(

    @Schema(description = "10글자 제한")
    String title,

    @Schema(description = "3000글자 제한")
    String content,

    @Schema(description = "0~6장")
    List<String> images,

    LocalDate writtenAt

) {
}
