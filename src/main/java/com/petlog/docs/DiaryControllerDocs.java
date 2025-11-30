package com.petlog.docs;

import com.petlog.auth.resolver.Authenticated;
import com.petlog.common.response.ApiResponse;
import com.petlog.diary.controller.dto.request.CreateDiaryRequestDto;
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

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "일기 생성에 성공하였습니다.")
    @Operation(summary = "일기 생성 API")
    ResponseEntity<ApiResponse<Void>> createDiary(@Authenticated final Long memberId, @PathVariable final Long groupId, @RequestBody final CreateDiaryRequestDto request);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "일기 전체 조회에 성공하였습니다.")
    @Operation(summary = "일기 전체 조회 API")
    ResponseEntity<ApiResponse<GetAllDiaryResponseDto>> getAllDiary(@Authenticated final Long memberId, @PathVariable final Long groupId);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "일기 상세 조회에 성공하였습니다.")
    @Operation(summary = "일기 상세 조회 API")
    ResponseEntity<ApiResponse<GetDiaryResponseDto>> getDiary(@Authenticated final Long memberId, @PathVariable final Long groupId, @PathVariable final Long diaryId);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "일기 상세 내용 수정에 성공하였습니다.")
    @Operation(summary = "일기 상세 내용 수정 API")
    ResponseEntity<ApiResponse<Void>> updateDiary(@Authenticated final Long memberId, @PathVariable final Long groupId, @PathVariable final Long diaryId, @RequestBody final UpdateDiaryRequestDto request);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "일기 삭제가 성공하였습니다.")
    @Operation(summary = "일기 삭제 API")
    ResponseEntity<ApiResponse<Void>> deleteDiary(@PathVariable final Long groupId, @PathVariable final Long diaryId);
}
