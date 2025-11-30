package com.petlog.diary.repository;

import com.petlog.diary.entity.Diary;
import com.petlog.petgroup.entity.PetGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findAllByPetGroup(PetGroup petGroup);
}
