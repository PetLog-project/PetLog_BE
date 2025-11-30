package com.petlog.docs;

import com.petlog.auth.resolver.Authenticated;
import com.petlog.common.response.ApiResponse;
import com.petlog.schedule.controller.dto.request.CreateScheduleRequestDto;
import com.petlog.schedule.controller.dto.request.UpdateScheduleRequestDto;
import com.petlog.schedule.controller.dto.response.GetMonthlyScheduleResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.YearMonth;

@Tag(name = "일정 API")
public interface ScheduleControllerDocs {

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "일정 생성에 성공하였습니다.")
    @Operation(summary = "일정 생성 API")
    ResponseEntity<ApiResponse<Void>> createSchedule(@Authenticated final Long memberId, @PathVariable final Long groupId, @RequestBody CreateScheduleRequestDto request);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "월별 일정 전체 조회에 성공하였습니다.")
    @Operation(summary = "월별 일정 전체 조회 API")
    ResponseEntity<ApiResponse<GetMonthlyScheduleResponseDto>> getAllSchedule(@Authenticated final Long memberId, @PathVariable final Long groupId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM") final YearMonth date);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "일정 상세 내용 수정에 성공하였습니다.")
    @Operation(summary = "일정 상세 내용 수정 API")
    ResponseEntity<ApiResponse<Void>> updateSchedule(@Authenticated final Long memberId, @PathVariable final Long groupId, @PathVariable final Long scheduleId, @RequestBody final UpdateScheduleRequestDto request);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "일정 삭제에 성공하였습니다.")
    @Operation(summary = "일정 삭제 API")
    ResponseEntity<ApiResponse<Void>> deleteSchedule(@PathVariable final Long groupId, @PathVariable final Long scheduleId);
}
