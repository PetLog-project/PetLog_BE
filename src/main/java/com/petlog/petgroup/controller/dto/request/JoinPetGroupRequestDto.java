package com.petlog.petgroup.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record JoinPetGroupRequestDto(

    @Schema(description = "6글자 제한")
    String joinCode

) {
}
