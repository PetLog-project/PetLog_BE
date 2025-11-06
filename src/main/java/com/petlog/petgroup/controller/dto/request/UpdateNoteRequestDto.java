package com.petlog.petgroup.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateNoteRequestDto(

    @Schema(description = "1000글자 제한")
    String note

) {
}
