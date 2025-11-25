package com.petlog.petgroup.controller;

import com.petlog.common.response.ApiResponse;
import com.petlog.docs.PetGroupControllerDocs;
import com.petlog.petgroup.controller.dto.request.CreatePetGroupRequestDto;
import com.petlog.petgroup.controller.dto.request.JoinPetGroupRequestDto;
import com.petlog.petgroup.controller.dto.request.UpdateNoteRequestDto;
import com.petlog.petgroup.controller.dto.response.CreatePetGroupResponseDto;
import com.petlog.petgroup.controller.dto.response.GetJoinCodeResponseDto;
import com.petlog.petgroup.controller.dto.response.GetNoteResponseDto;
import com.petlog.petgroup.controller.dto.response.JoinPetGroupResponseDto;
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
import static com.petlog.petgroup.controller.PetGroupSuccessCode.GET_JOIN_CODE;
import static com.petlog.petgroup.controller.PetGroupSuccessCode.GET_NOTE;
import static com.petlog.petgroup.controller.PetGroupSuccessCode.JOIN_PET_GROUP;
import static com.petlog.petgroup.controller.PetGroupSuccessCode.LEAVE_PET_GROUP;
import static com.petlog.petgroup.controller.PetGroupSuccessCode.UPDATE_NOTE;

@RequiredArgsConstructor
@RequestMapping("/api/groups")
@RestController
public class PetGroupController implements PetGroupControllerDocs {

    @PostMapping
    public ResponseEntity<ApiResponse<CreatePetGroupResponseDto>> createPetGroup(
        @RequestBody final CreatePetGroupRequestDto request
    ) {
        CreatePetGroupResponseDto response = new CreatePetGroupResponseDto(1L);

        return ResponseEntity.ok(
            ApiResponse.successWithData(CREATE_PET_GROUP, response)
        );
    }

    @PostMapping("/join")
    public ResponseEntity<ApiResponse<JoinPetGroupResponseDto>> joinPetGroup(
        @RequestBody final JoinPetGroupRequestDto request
    ) {
        JoinPetGroupResponseDto response = new JoinPetGroupResponseDto(1L);

        return ResponseEntity.ok(
            ApiResponse.successWithData(JOIN_PET_GROUP, response)
        );
    }

    @DeleteMapping("/{groupId}/leave")
    public ResponseEntity<ApiResponse<Void>> leavePetGroup(
        @PathVariable final Long groupId
    ) {
        return ResponseEntity.ok(
            ApiResponse.success(LEAVE_PET_GROUP)
        );
    }

    @GetMapping("/{groupId}/invite")
    public ResponseEntity<ApiResponse<GetJoinCodeResponseDto>> getJoinCode(
        @PathVariable final Long groupId
    ) {
        final GetJoinCodeResponseDto response = new GetJoinCodeResponseDto("123ABC");

        return ResponseEntity.ok(
            ApiResponse.successWithData(GET_JOIN_CODE, response)
        );
    }

    @GetMapping("/{groupId}/note")
    public ResponseEntity<ApiResponse<GetNoteResponseDto>> getNote(
        @PathVariable final Long groupId
    ) {
        final GetNoteResponseDto response = new GetNoteResponseDto("우리 여름이는 엄청 예쁘구~");

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
