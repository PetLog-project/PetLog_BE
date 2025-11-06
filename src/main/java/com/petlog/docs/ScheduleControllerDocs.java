package com.petlog.docs;

import com.petlog.common.response.ApiResponse;
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

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "월별 일정 전체 조회에 성공하였습니다.")
    @Operation(summary = "월별 일정 전체 조회 API")
    ResponseEntity<ApiResponse<GetMonthlyScheduleResponseDto>> getAllSchedule(@PathVariable Long groupId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth date);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "일정 상세 내용 수정에 성공하였습니다.")
    @Operation(summary = "일정 상세 내용 수정 API")
    ResponseEntity<ApiResponse<Void>> updateSchedule(@PathVariable Long groupId, @PathVariable Long scheduleId, @RequestBody UpdateScheduleRequestDto request);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "일정 삭제에 성공하였습니다.")
    @Operation(summary = "일정 삭제 API")
    ResponseEntity<ApiResponse<Void>> deleteSchedule(@PathVariable Long groupId, @PathVariable Long scheduleId);
}
