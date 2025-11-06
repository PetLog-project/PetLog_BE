package com.petlog.diary.controller;

import com.petlog.common.response.ApiResponse;
import com.petlog.diary.controller.dto.request.UpdateDiaryRequestDto;
import com.petlog.diary.controller.dto.response.GetAllDiaryResponseDto;
import com.petlog.diary.controller.dto.response.GetDiaryResponseDto;
import com.petlog.diary.service.dto.GetDailyDiaryDto;
import com.petlog.diary.service.dto.GetDiaryInfoDto;
import com.petlog.docs.DiaryControllerDocs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static com.petlog.diary.controller.DiarySuccessCode.DELETE_DIARY;
import static com.petlog.diary.controller.DiarySuccessCode.GET_ALL_DIARY;
import static com.petlog.diary.controller.DiarySuccessCode.GET_DIARY;
import static com.petlog.diary.controller.DiarySuccessCode.UPDATE_DIARY;

@RequiredArgsConstructor
@RequestMapping("/api/groups/{groupId}/diary")
@RestController
public class DiaryController implements DiaryControllerDocs {

    @GetMapping
    public ResponseEntity<ApiResponse<GetAllDiaryResponseDto>> getAllDiary(
        @PathVariable final Long groupId
    ) {
        final List<GetDiaryInfoDto> dailyDiary1 = List.of(
            new GetDiaryInfoDto(1L, "잠자는 아이", "https://잠자는 여름이.png"),
            new GetDiaryInfoDto(2L, "날뛰는 아이", "https://날뛰는 여름이.png")
        );

        final GetDailyDiaryDto dailyDiary = new GetDailyDiaryDto(LocalDate.now(), dailyDiary1);

        final List<GetDailyDiaryDto> allDailyDiary = List.of(dailyDiary);

        final GetAllDiaryResponseDto allDiary = new GetAllDiaryResponseDto(allDailyDiary);

        return ResponseEntity.ok(
            ApiResponse.successWithData(GET_ALL_DIARY, allDiary)
        );
    }

    @GetMapping("/{diaryId}")
    public ResponseEntity<ApiResponse<GetDiaryResponseDto>> getDiary(
        @PathVariable final Long groupId,
        @PathVariable final Long diaryId
    ) {
        final GetDiaryResponseDto response = new GetDiaryResponseDto("제목", "내용", List.of("이미지"), LocalDate.now(), "서은");

        return ResponseEntity.ok(
            ApiResponse.successWithData(GET_DIARY, response)
        );
    }

    @PatchMapping("/{diaryId}")
    public ResponseEntity<ApiResponse<Void>> updateDiary(
        @PathVariable final Long groupId,
        @PathVariable final Long diaryId,
        @RequestBody final UpdateDiaryRequestDto request
    ) {
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
