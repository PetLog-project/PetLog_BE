package com.petlog.schedule.service.dto;

import com.petlog.schedule.entity.ScheduleType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record GetScheduleInfoDto(

    Long scheduleId,

    @Schema(description = "10글자 제한")
    String title,

    boolean isAllDay,

    LocalDateTime startTime,

    LocalDateTime endTime,

    @Schema(description = "YELLOW/GREEN/BLUE")
    ScheduleType tag,

    LocalDateTime remindNotificationAt,

    @Schema(description = "300글자 제한")
    String memo

) {
}
