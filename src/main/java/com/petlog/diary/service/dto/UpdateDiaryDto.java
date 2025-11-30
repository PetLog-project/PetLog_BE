package com.petlog.diary.service.dto;

import java.time.LocalDate;
import java.util.List;

public record UpdateDiaryDto(

    String title,
    String content,
    List<String> images,
    LocalDate writtenAt

) {
}
