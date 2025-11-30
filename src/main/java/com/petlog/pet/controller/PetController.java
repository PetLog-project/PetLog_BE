package com.petlog.pet.controller;

import com.petlog.auth.resolver.Authenticated;
import com.petlog.common.response.ApiResponse;
import com.petlog.docs.PetControllerDocs;
import com.petlog.pet.controller.dto.request.CreateFeedingRecordRequestDto;
import com.petlog.pet.controller.dto.request.CreatePoopRecordRequestDto;
import com.petlog.pet.controller.dto.request.CreateWateringRecordRequestDto;
import com.petlog.pet.controller.dto.request.UpdatePetProfileRequestDto;
import com.petlog.pet.controller.dto.response.GetPetInfoResponseDto;
import com.petlog.pet.service.PetService;
import com.petlog.pet.service.dto.GetFeedingInfoDto;
import com.petlog.pet.service.dto.GetPetProfileDto;
import com.petlog.pet.service.dto.GetPoopInfoDto;
import com.petlog.pet.service.dto.GetWateringInfoDto;
import com.petlog.pet.service.dto.UpdatePetProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.petlog.pet.controller.PetSuccessCode.CREATE_FEEDING_RECORD;
import static com.petlog.pet.controller.PetSuccessCode.CREATE_POOP_RECORD;
import static com.petlog.pet.controller.PetSuccessCode.CREATE_WATERING_RECORD;
import static com.petlog.pet.controller.PetSuccessCode.GET_PET_INFO;
import static com.petlog.pet.controller.PetSuccessCode.UPDATE_PET_PROFILE;

@RequiredArgsConstructor
@RequestMapping("/api/groups")
@RestController
public class PetController implements PetControllerDocs {

    private final PetService petService;

    @GetMapping("/{groupId}/pet")
    public ResponseEntity<ApiResponse<GetPetInfoResponseDto>> getPetInfo(
        @Authenticated final Long memberId,
        @PathVariable final Long groupId
    ) {
        final GetPetProfileDto profile = petService.getPetProfile(memberId, groupId);
        final GetFeedingInfoDto feedingInfo = petService.getFeedingInfo(memberId, groupId);
        final GetWateringInfoDto wateringInfo = petService.getWateringInfo(memberId, groupId);
        final GetPoopInfoDto poopInfo = petService.getPoopInfo(memberId, groupId);

        final GetPetInfoResponseDto response = new GetPetInfoResponseDto(profile, feedingInfo, wateringInfo, poopInfo);

        return ResponseEntity.ok(
            ApiResponse.successWithData(GET_PET_INFO, response)
        );
    }

    @PatchMapping("/{groupId}/pet")
    public ResponseEntity<ApiResponse<Void>> updatePetProfile(
        @Authenticated final Long memberId,
        @PathVariable final Long groupId,
        @RequestBody final UpdatePetProfileRequestDto request
    ) {
        final UpdatePetProfileDto dto = new UpdatePetProfileDto(
            request.imageUrl(),
            request.name(),
            request.age(),
            request.weight(),
            request.gender()
        );
        petService.updatePetProfile(memberId, groupId, dto);

        return ResponseEntity.ok(
            ApiResponse.success(UPDATE_PET_PROFILE)
        );
    }

    @PostMapping("/{groupId}/pet/feeding")
    public ResponseEntity<ApiResponse<Void>> createFeedingRecord(
        @Authenticated final Long memberId,
        @PathVariable final Long groupId,
        @RequestBody final CreateFeedingRecordRequestDto request
    ) {
        return ResponseEntity.ok(
            ApiResponse.success(CREATE_FEEDING_RECORD)
        );
    }

    @PostMapping("/{groupId}/pet/watering")
    public ResponseEntity<ApiResponse<Void>> createWateringRecord(
        @Authenticated final Long memberId,
        @PathVariable final Long groupId,
        @RequestBody final CreateWateringRecordRequestDto request
    ) {
        return ResponseEntity.ok(
            ApiResponse.success(CREATE_WATERING_RECORD)
        );
    }

    @PostMapping("/{groupId}/pet/poop")
    public ResponseEntity<ApiResponse<Void>> createPoopRecord(
        @Authenticated final Long memberId,
        @PathVariable final Long groupId,
        @RequestBody final CreatePoopRecordRequestDto request
    ) {
        return ResponseEntity.ok(
            ApiResponse.success(CREATE_POOP_RECORD)
        );
    }
}
