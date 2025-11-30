package com.petlog.schedule.service.dto;

import com.petlog.schedule.entity.ScheduleType;

import java.time.LocalDateTime;

public record UpdateScheduleDto(

    String title,
    boolean isAllDay,
    LocalDateTime startTime,
    LocalDateTime endTime,
    LocalDateTime remindNotificationAt,
    ScheduleType tag,
    String memo

) {
}
