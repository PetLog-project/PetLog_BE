package com.petlog.docs;

import com.petlog.common.response.ApiResponse;
import com.petlog.diary.controller.dto.request.UpdateDiaryRequestDto;
import com.petlog.diary.controller.dto.response.GetAllDiaryResponseDto;
import com.petlog.diary.controller.dto.response.GetDiaryResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "일기 API")
public interface DiaryControllerDocs {

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "일기 전체 조회에 성공하였습니다.")
    @Operation(summary = "일기 전체 조회 API")
    ResponseEntity<ApiResponse<GetAllDiaryResponseDto>> getAllDiary(@PathVariable final Long groupId);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "일기 상세 조회에 성공하였습니다.")
    @Operation(summary = "일기 상세 조회 API")
    ResponseEntity<ApiResponse<GetDiaryResponseDto>> getDiary(@PathVariable final Long groupId, @PathVariable final Long diaryId);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "일기 상세 내용 수정에 성공하였습니다.")
    @Operation(summary = "일기 상세 내용 수정 API")
    ResponseEntity<ApiResponse<Void>> updateDiary(@PathVariable final Long groupId, @PathVariable final Long diaryId, @RequestBody final UpdateDiaryRequestDto request);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "일기 삭제가 성공하였습니다.")
    @Operation(summary = "일기 삭제 API")
    ResponseEntity<ApiResponse<Void>> deleteDiary(@PathVariable final Long groupId, @PathVariable final Long diaryId);
}
