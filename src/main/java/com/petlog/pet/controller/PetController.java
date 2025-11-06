package com.petlog.pet.controller;

import com.petlog.common.response.ApiResponse;
import com.petlog.docs.PetControllerDocs;
import com.petlog.pet.controller.dto.request.UpdatePetProfileRequestDto;
import com.petlog.pet.controller.dto.response.GetPetInfoResponseDto;
import com.petlog.pet.service.dto.GetFeedingInfoDto;
import com.petlog.pet.service.dto.GetPetProfileDto;
import com.petlog.pet.service.dto.GetPoopInfoDto;
import com.petlog.pet.service.dto.GetWateringInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static com.petlog.pet.controller.PetSuccessCode.GET_PET_INFO;
import static com.petlog.pet.controller.PetSuccessCode.UPDATE_PET_PROFILE;

@RequiredArgsConstructor
@RestController
public class PetController implements PetControllerDocs {

    @GetMapping("/api/groups/{groupId}/pet")
    public ResponseEntity<ApiResponse<GetPetInfoResponseDto>> getPetInfo(
        @PathVariable final Long groupId
    ) {
        GetPetProfileDto profile = new GetPetProfileDto("https://여름.png", "여름", "4개월", "1kg", "FEMALE");
        GetFeedingInfoDto feedingInfo = new GetFeedingInfoDto(6, LocalDateTime.now(), "서은", "밥 적당히 줄 것");
        GetWateringInfoDto wateringInfo = new GetWateringInfoDto(6, LocalDateTime.now(), "서은", "밥 줄 때 같이");
        GetPoopInfoDto poopInfo = new GetPoopInfoDto(3, "서은", "건강하네");

        GetPetInfoResponseDto response = new GetPetInfoResponseDto(profile, feedingInfo, wateringInfo, poopInfo);

        return ResponseEntity.ok(
            ApiResponse.successWithData(GET_PET_INFO, response)
        );
    }

    @PatchMapping("/api/groups/{groupId}/pet")
    public ResponseEntity<ApiResponse<Void>> updatePetProfile(
        @PathVariable final Long groupId,
        @RequestBody final UpdatePetProfileRequestDto request
    ) {
        return ResponseEntity.ok(
            ApiResponse.success(UPDATE_PET_PROFILE)
        );
    }
}
