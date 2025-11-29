package com.petlog.petgroup.controller;

import com.petlog.auth.resolver.Authenticated;
import com.petlog.common.response.ApiResponse;
import com.petlog.docs.PetGroupControllerDocs;
import com.petlog.petgroup.controller.dto.request.CreatePetGroupRequestDto;
import com.petlog.petgroup.controller.dto.request.JoinPetGroupRequestDto;
import com.petlog.petgroup.controller.dto.request.UpdateNoteRequestDto;
import com.petlog.petgroup.controller.dto.response.GetJoinCodeResponseDto;
import com.petlog.petgroup.controller.dto.response.GetJoiningPetGroupResponseDto;
import com.petlog.petgroup.controller.dto.response.GetNoteResponseDto;
import com.petlog.petgroup.service.PetGroupService;
import com.petlog.petgroup.service.dto.CreatePetGroupDto;
import com.petlog.petgroup.service.dto.GetJoinCodeDto;
import com.petlog.petgroup.service.dto.GetMyPetGroupDto;
import com.petlog.petgroup.service.dto.GetNoteDto;
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

import static com.petlog.petgroup.controller.PetGroupSuccessCode.CREATE_PET_GROUP;
import static com.petlog.petgroup.controller.PetGroupSuccessCode.GET_JOINING_PET_GROUP;
import static com.petlog.petgroup.controller.PetGroupSuccessCode.GET_JOIN_CODE;
import static com.petlog.petgroup.controller.PetGroupSuccessCode.GET_NOTE;
import static com.petlog.petgroup.controller.PetGroupSuccessCode.JOIN_PET_GROUP;
import static com.petlog.petgroup.controller.PetGroupSuccessCode.LEAVE_PET_GROUP;
import static com.petlog.petgroup.controller.PetGroupSuccessCode.UPDATE_NOTE;

@RequiredArgsConstructor
@RequestMapping("/api/groups")
@RestController
public class PetGroupController implements PetGroupControllerDocs {

    private final PetGroupService petGroupService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createPetGroup(
        @Authenticated final Long memberId,
        @RequestBody final CreatePetGroupRequestDto request
    ) {
        final CreatePetGroupDto dto = CreatePetGroupDto.from(request);
        petGroupService.createPetGroup(memberId, dto);

        return ResponseEntity.ok(
            ApiResponse.success(CREATE_PET_GROUP)
        );
    }

    @PostMapping("/join")
    public ResponseEntity<ApiResponse<Void>> joinPetGroup(
        @Authenticated final Long memberId,
        @RequestBody final JoinPetGroupRequestDto request
    ) {
        petGroupService.joinPetGroup(memberId, request.joinCode());

        return ResponseEntity.ok(
            ApiResponse.success(JOIN_PET_GROUP)
        );
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<GetJoiningPetGroupResponseDto>> getJoiningPetGroup(
        @Authenticated final Long memberId
    ) {
        final GetMyPetGroupDto dto = petGroupService.getMyPetGroups(memberId);
        final GetJoiningPetGroupResponseDto response = new GetJoiningPetGroupResponseDto(dto.groupIds());

        return ResponseEntity.ok(
            ApiResponse.successWithData(GET_JOINING_PET_GROUP, response)
        );
    }

    @DeleteMapping("/{groupId}/leave")
    public ResponseEntity<ApiResponse<Void>> leavePetGroup(
        @Authenticated final Long memberId,
        @PathVariable final Long groupId
    ) {
        petGroupService.leavePetGroup(memberId, groupId);

        return ResponseEntity.ok(
            ApiResponse.success(LEAVE_PET_GROUP)
        );
    }

    @GetMapping("/{groupId}/invite")
    public ResponseEntity<ApiResponse<GetJoinCodeResponseDto>> getJoinCode(
        @Authenticated final Long memberId,
        @PathVariable final Long groupId
    ) {
        final GetJoinCodeDto dto = petGroupService.getPetGroupJoinCode(memberId, groupId);
        final GetJoinCodeResponseDto response = new GetJoinCodeResponseDto(dto.joinCode());

        return ResponseEntity.ok(
            ApiResponse.successWithData(GET_JOIN_CODE, response)
        );
    }

    @GetMapping("/{groupId}/note")
    public ResponseEntity<ApiResponse<GetNoteResponseDto>> getNote(
        @Authenticated final Long memberId,
        @PathVariable final Long groupId
    ) {
        final GetNoteDto dto = petGroupService.getNote(memberId, groupId);
        final GetNoteResponseDto response = new GetNoteResponseDto(dto.note());

        return ResponseEntity.ok(
            ApiResponse.successWithData(GET_NOTE, response)
        );
    }

    @PatchMapping("/{groupId}/note")
    public ResponseEntity<ApiResponse<Void>> updateNote(
        @PathVariable final Long groupId,
        @RequestBody final UpdateNoteRequestDto request
    ) {
        return ResponseEntity.ok(
            ApiResponse.success(UPDATE_NOTE)
        );
    }
}
