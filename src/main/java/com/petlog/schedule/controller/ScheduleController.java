package com.petlog.schedule.controller;

import com.petlog.common.response.ApiResponse;
import com.petlog.docs.ScheduleControllerDocs;
import com.petlog.schedule.controller.dto.request.UpdateScheduleRequestDto;
import com.petlog.schedule.controller.dto.response.GetMonthlyScheduleResponseDto;
import com.petlog.schedule.entity.ScheduleType;
import com.petlog.schedule.service.dto.GetDailyScheduleDto;
import com.petlog.schedule.service.dto.GetScheduleInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

import static com.petlog.schedule.controller.ScheduleSuccessCode.DELETE_SCHEDULE;
import static com.petlog.schedule.controller.ScheduleSuccessCode.GET_ALL_SCHEDULE;
import static com.petlog.schedule.controller.ScheduleSuccessCode.UPDATE_SCHEDULE;

@RequiredArgsConstructor
@RequestMapping("/api/groups/{groupId}/schedule")
@RestController
public class ScheduleController implements ScheduleControllerDocs {

    @GetMapping
    public ResponseEntity<ApiResponse<GetMonthlyScheduleResponseDto>> getAllSchedule(
        @PathVariable final Long groupId,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM") final YearMonth date
    ) {

        final GetScheduleInfoDto dto = new GetScheduleInfoDto(1L, "병원", true, LocalDateTime.now(), LocalDateTime.now(), ScheduleType.BLUE, LocalDateTime.now(), "memo");

        final GetDailyScheduleDto daily = new GetDailyScheduleDto(LocalDate.now(), List.of(dto));

        final GetMonthlyScheduleResponseDto response = new GetMonthlyScheduleResponseDto(List.of(daily));

        return ResponseEntity.ok(
            ApiResponse.successWithData(GET_ALL_SCHEDULE, response)
        );
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ApiResponse<Void>> updateSchedule(
        @PathVariable final Long groupId,
        @PathVariable final Long scheduleId,
        @RequestBody final UpdateScheduleRequestDto request
    ) {
        return ResponseEntity.ok(
            ApiResponse.success(UPDATE_SCHEDULE)
        );
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<ApiResponse<Void>> deleteSchedule(
        @PathVariable final Long groupId,
        @PathVariable final Long scheduleId
    ) {
        return ResponseEntity.ok(
            ApiResponse.success(DELETE_SCHEDULE)
        );
    }
}
