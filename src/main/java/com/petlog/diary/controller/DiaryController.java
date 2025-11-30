package com.petlog.diary.controller;

import com.petlog.auth.resolver.Authenticated;
import com.petlog.common.response.ApiResponse;
import com.petlog.diary.controller.dto.request.CreateDiaryRequestDto;
import com.petlog.diary.controller.dto.request.UpdateDiaryRequestDto;
import com.petlog.diary.controller.dto.response.GetAllDiaryResponseDto;
import com.petlog.diary.controller.dto.response.GetDiaryResponseDto;
import com.petlog.diary.service.DiaryService;
import com.petlog.diary.service.dto.CreateDiaryDto;
import com.petlog.diary.service.dto.GetDiaryDto;
import com.petlog.diary.service.dto.GetDiaryInfoDto;
import com.petlog.diary.service.dto.UpdateDiaryDto;
import com.petlog.docs.DiaryControllerDocs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.petlog.diary.controller.DiarySuccessCode.CREATE_DIARY;
import static com.petlog.diary.controller.DiarySuccessCode.DELETE_DIARY;
import static com.petlog.diary.controller.DiarySuccessCode.GET_ALL_DIARY;
import static com.petlog.diary.controller.DiarySuccessCode.GET_DIARY;
import static com.petlog.diary.controller.DiarySuccessCode.UPDATE_DIARY;

@RequiredArgsConstructor
@RequestMapping("/api/groups/{groupId}/diary")
@RestController
public class DiaryController implements DiaryControllerDocs {

    private final DiaryService diaryService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createDiary(
        @Authenticated final Long memberId,
        @PathVariable final Long groupId,
        @RequestBody final CreateDiaryRequestDto request
    ) {
        final CreateDiaryDto dto = new CreateDiaryDto(
            request.title(),
            request.content(),
            request.images(),
            request.writtenAt()
        );
        diaryService.createDiary(memberId, groupId, dto);

        return ResponseEntity.ok(
            ApiResponse.success(CREATE_DIARY)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<GetAllDiaryResponseDto>> getAllDiary(
        @Authenticated final Long memberId,
        @PathVariable final Long groupId
    ) {
        final List<GetDiaryInfoDto> diaries = diaryService.getAllDiaries(memberId, groupId);
        final GetAllDiaryResponseDto allDiary = GetAllDiaryResponseDto.from(diaries);

        return ResponseEntity.ok(
            ApiResponse.successWithData(GET_ALL_DIARY, allDiary)
        );
    }

    @GetMapping("/{diaryId}")
    public ResponseEntity<ApiResponse<GetDiaryResponseDto>> getDiary(
        @Authenticated final Long memberId,
        @PathVariable final Long groupId,
        @PathVariable final Long diaryId
    ) {
        final GetDiaryDto dto = diaryService.getDiary(memberId, groupId, diaryId);
        final GetDiaryResponseDto response = new GetDiaryResponseDto(
            dto.title(),
            dto.content(),
            dto.images(),
            dto.writtenAt(),
            dto.writerName()
        );

        return ResponseEntity.ok(
            ApiResponse.successWithData(GET_DIARY, response)
        );
    }

    @PatchMapping("/{diaryId}")
    public ResponseEntity<ApiResponse<Void>> updateDiary(
        @Authenticated final Long memberId,
        @PathVariable final Long groupId,
        @PathVariable final Long diaryId,
        @RequestBody final UpdateDiaryRequestDto request
    ) {
        final UpdateDiaryDto dto = new UpdateDiaryDto(
            request.title(),
            request.content(),
            request.images(),
            request.writtenAt()
        );
        diaryService.updateDiary(memberId, groupId, diaryId, dto);

        return ResponseEntity.ok(
            ApiResponse.success(UPDATE_DIARY)
        );
    }

    @DeleteMapping("/{diaryId}")
    public ResponseEntity<ApiResponse<Void>> deleteDiary(
        @PathVariable final Long groupId,
        @PathVariable final Long diaryId
    ) {
        return ResponseEntity.ok(
            ApiResponse.success(DELETE_DIARY)
        );
    }
}
