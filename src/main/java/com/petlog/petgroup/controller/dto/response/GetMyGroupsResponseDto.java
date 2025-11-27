package com.petlog.petgroup.controller.dto.response;

import java.util.List;

public record GetMyGroupsResponseDto(

    List<Long> groupIds

) {
}
