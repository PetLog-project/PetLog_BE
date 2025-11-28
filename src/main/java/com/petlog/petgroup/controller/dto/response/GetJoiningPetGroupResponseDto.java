package com.petlog.petgroup.controller.dto.response;

import java.util.List;

public record GetJoiningPetGroupResponseDto(

    List<Long> groupIds

) {
}
