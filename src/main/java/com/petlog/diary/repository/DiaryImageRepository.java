package com.petlog.diary.repository;

import com.petlog.diary.entity.Diary;
import com.petlog.diary.entity.DiaryImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryImageRepository extends JpaRepository<DiaryImage, Long> {

    void deleteAllByDiary(final Diary diary);
}
