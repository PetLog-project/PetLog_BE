package com.petlog.pet.controller.dto.response;

import com.petlog.pet.service.dto.GetFeedingInfoDto;
import com.petlog.pet.service.dto.GetPetProfileDto;
import com.petlog.pet.service.dto.GetPoopInfoDto;
import com.petlog.pet.service.dto.GetWateringInfoDto;

public record GetPetInfoResponseDto(

    GetPetProfileDto profile,
    GetFeedingInfoDto feedingInfo,
    GetWateringInfoDto wateringInfo,
    GetPoopInfoDto poopInfo

) {
}
