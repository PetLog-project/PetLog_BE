package com.petlog.schedule.service.dto;

import java.time.LocalDate;
import java.util.List;

public record GetDailyScheduleDto(

    LocalDate date,
    List<GetScheduleInfoDto> schedules

) {
}
