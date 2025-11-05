package com.petlog.member.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateIsNotificationEnabledRequestDto(

    @Schema(description = "알림 수신 여부")
    boolean isNotificationEnabled
) {
}
