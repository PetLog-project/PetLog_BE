package com.petlog.schedule.service.dto;

import com.petlog.schedule.entity.ScheduleType;

import java.time.LocalDateTime;

public record CreateScheduleDto(

    String title,
    boolean isAllDay,
    LocalDateTime startTime,
    LocalDateTime endTime,
    ScheduleType tag,
    LocalDateTime remindNotificationAt,
    String memo

) {
}
