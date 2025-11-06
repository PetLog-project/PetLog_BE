package com.petlog.schedule.controller.dto.response;

import com.petlog.schedule.service.dto.GetDailyScheduleDto;

import java.util.List;

public record GetMonthlyScheduleResponseDto(

    List<GetDailyScheduleDto> monthlySchedules

) {
}
