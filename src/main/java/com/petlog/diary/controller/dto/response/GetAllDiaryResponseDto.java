package com.petlog.diary.controller.dto.response;

import com.petlog.diary.service.dto.GetDailyDiaryDto;
import com.petlog.diary.service.dto.GetDiaryInfoDto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public record GetAllDiaryResponseDto(

    List<GetDailyDiaryDto> diary

) {

    public static GetAllDiaryResponseDto from(List<GetDiaryInfoDto> diaryList) {

        List<GetDailyDiaryDto> grouped =
            diaryList.stream()
                .collect(Collectors.groupingBy(GetDiaryInfoDto::writtenAt))
                .entrySet()
                .stream()
                .map(entry ->
                    new GetDailyDiaryDto(
                        entry.getKey(),
                        entry.getValue()
                    )
                )
                .sorted(Comparator.comparing(GetDailyDiaryDto::writtenAt).reversed())
                .toList();

        return new GetAllDiaryResponseDto(grouped);
    }
}
