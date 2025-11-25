package com.petlog.docs;

import com.petlog.common.response.ApiResponse;
import com.petlog.petgroup.controller.dto.request.CreatePetGroupRequestDto;
import com.petlog.petgroup.controller.dto.request.UpdateNoteRequestDto;
import com.petlog.petgroup.controller.dto.response.CreatePetGroupResponseDto;
import com.petlog.petgroup.controller.dto.response.GetJoinCodeResponseDto;
import com.petlog.petgroup.controller.dto.response.GetNoteResponseDto;
import com.petlog.petgroup.controller.dto.request.JoinPetGroupRequestDto;
import com.petlog.petgroup.controller.dto.response.JoinPetGroupResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "그룹 API")
public interface PetGroupControllerDocs {

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "그룹 생성에 성공하였습니다.")
    @Operation(summary = "그룹 생성 API")
    ResponseEntity<ApiResponse<CreatePetGroupResponseDto>> createPetGroup(@RequestBody final CreatePetGroupRequestDto request);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "그룹 참여에 성공하였습니다.")
    @Operation(summary = "그룹 참여 API")
    ResponseEntity<ApiResponse<JoinPetGroupResponseDto>> joinPetGroup(@RequestBody final JoinPetGroupRequestDto request);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "그룹 탈퇴에 성공하였습니다.")
    @Operation(summary = "그룹 탈퇴 API")
    ResponseEntity<ApiResponse<Void>> leavePetGroup(@PathVariable final Long groupId);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "초대코드 조회에 성공하였습니다.")
    @Operation(summary = "초대코드 조회 API")
    ResponseEntity<ApiResponse<GetJoinCodeResponseDto>> getJoinCode(@PathVariable final Long groupId);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "참고사항 조회에 성공하였습니다.")
    @Operation(summary = "참고사항 조회 API")
    ResponseEntity<ApiResponse<GetNoteResponseDto>> getNote(@PathVariable final Long groupId);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "참고사항 수정에 성공하였습니다.")
    @Operation(summary = "참고사항 수정 API")
    ResponseEntity<ApiResponse<Void>> updateNote(@PathVariable final Long groupId, @RequestBody final UpdateNoteRequestDto request);
}
