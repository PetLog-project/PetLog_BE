package com.petlog.schedule.controller.dto.request;

import com.petlog.schedule.entity.ScheduleType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record UpdateScheduleRequestDto(

    @Schema(description = "10글자 제한")
    String title,

    boolean isAllDay,

    @Schema(description = "isAllDay가 true면 해당 날짜의 자정 시간으로 보내야함")
    LocalDateTime startTime,

    @Schema(description = "isAllDay가 true면 해당 날짜의 자정 시간으로 보내야함")
    LocalDateTime endTime,

    @Schema(description = "YELLOW/GREEN/BLUE")
    ScheduleType tag,

    LocalDateTime remindNotificationAt,

    @Schema(description = "300글자 제한")
    String memo

) {
}
