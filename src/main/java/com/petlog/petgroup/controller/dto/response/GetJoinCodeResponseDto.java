package com.petlog.petgroup.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetJoinCodeResponseDto(

    @Schema(description = "6글자 제한")
    String joinCode

) {

    public GetJoinCodeResponseDto(final String joinCode) {
        this.joinCode = joinCode;
    }
}
