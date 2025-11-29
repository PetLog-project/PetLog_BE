package com.petlog.petgroup.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetNoteResponseDto(

    @Schema(description = "1000글자 제한")
    String note

) {
}
