package com.petlog.pet.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateFeedingRecordRequestDto(

    @Schema(description = "100글자 제한")
    String memo

) {
}
