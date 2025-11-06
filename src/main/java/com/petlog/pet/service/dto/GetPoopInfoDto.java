package com.petlog.pet.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetPoopInfoDto(

    int todayPoopCount,

    String lastCheckerName,

    @Schema(description = "100글자 제한")
    String lastMemo

) {
    public GetPoopInfoDto(
        final int todayPoopCount,
        final String lastCheckerName,
        final String lastMemo
    ) {
        this.todayPoopCount = todayPoopCount;
        this.lastCheckerName = lastCheckerName;
        this.lastMemo = lastMemo;
    }
}
