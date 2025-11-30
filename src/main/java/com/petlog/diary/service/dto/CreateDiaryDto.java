package com.petlog.diary.service.dto;

import java.time.LocalDate;
import java.util.List;

public record CreateDiaryDto(

    String title,
    String content,
    List<String> images,
    LocalDate writtenAt

) {
}
