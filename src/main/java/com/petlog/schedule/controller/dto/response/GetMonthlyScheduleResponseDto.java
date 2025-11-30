package com.petlog.schedule.controller.dto.response;

import com.petlog.schedule.service.dto.GetDailyScheduleDto;
import com.petlog.schedule.service.dto.GetScheduleInfoDto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public record GetMonthlyScheduleResponseDto(

    List<GetDailyScheduleDto> monthlySchedules

) {

    public static GetMonthlyScheduleResponseDto from(final List<GetScheduleInfoDto> schedules) {

        List<GetDailyScheduleDto> grouped =
            schedules.stream()
                .collect(Collectors.groupingBy(s -> s.startAt().toLocalDate()))
                .entrySet()
                .stream()
                .map(entry ->
                    new GetDailyScheduleDto(
                        entry.getKey(),
                        entry.getValue()
                    )
                )
                .sorted(Comparator.comparing(GetDailyScheduleDto::date))
                .toList();

        return new GetMonthlyScheduleResponseDto(grouped);
    }
}
