package com.petlog.docs;

import com.petlog.auth.resolver.Authenticated;
import com.petlog.common.response.ApiResponse;
import com.petlog.pet.controller.dto.request.CreateFeedingRecordRequestDto;
import com.petlog.pet.controller.dto.request.CreatePoopRecordRequestDto;
import com.petlog.pet.controller.dto.request.CreateWateringRecordRequestDto;
import com.petlog.pet.controller.dto.request.UpdatePetProfileRequestDto;
import com.petlog.pet.controller.dto.response.GetPetInfoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "반려동물 API")
public interface PetControllerDocs {

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "반려동물 정보 조회에 성공하였습니다.")
    @Operation(summary = "반려동물 정보 조회 API")
    ResponseEntity<ApiResponse<GetPetInfoResponseDto>> getPetInfo(@Authenticated final Long memberId, @PathVariable final Long groupId);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "반려동물 정보 수정에 성공하였습니다.")
    @Operation(summary = "반려동물 정보 수정 API")
    ResponseEntity<ApiResponse<Void>> updatePetProfile(@Authenticated final Long memberId, @PathVariable final Long groupId, @RequestBody final UpdatePetProfileRequestDto request);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "밥 준 기록 등록에 성공하였습니다.")
    @Operation(summary = "밥 준 기록 등록 API")
    ResponseEntity<ApiResponse<Void>> createFeedingRecord(@Authenticated final Long memberId, @PathVariable final Long groupId, @RequestBody final CreateFeedingRecordRequestDto request);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "물 준 기록 등록에 성공하였습니다.")
    @Operation(summary = "물 준 기록 등록 API")
    ResponseEntity<ApiResponse<Void>> createWateringRecord(@Authenticated final Long memberId, @PathVariable final Long groupId, @RequestBody final CreateWateringRecordRequestDto request);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "배변 기록 등록에 성공하였습니다.")
    @Operation(summary = "배변 기록 등록 API")
    ResponseEntity<ApiResponse<Void>> createPoopRecord(@Authenticated final Long memberId, @PathVariable final Long groupId, @RequestBody final CreatePoopRecordRequestDto request);
}
