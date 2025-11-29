package com.petlog.docs;

import com.petlog.auth.resolver.Authenticated;
import com.petlog.common.response.ApiResponse;
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
    ResponseEntity<ApiResponse<Void>> updatePetProfile(@PathVariable final Long groupId, @RequestBody final UpdatePetProfileRequestDto request);
}
