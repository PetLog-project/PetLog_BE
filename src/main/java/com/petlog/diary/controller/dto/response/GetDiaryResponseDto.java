package com.petlog.diary.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public record GetDiaryResponseDto(

    @Schema(description = "10글자 제한")
    String title,

    @Schema(description = "3000글자 제한")
    String content,

    @Schema(description = "0~6장")
    List<String> images,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    LocalDate writtenAt,

    String writerName

) {
}
