package com.petlog.pet.repository;

import com.petlog.pet.entity.FeedingDailyRecord;
import com.petlog.pet.entity.PetProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedingDailyRecordRepository extends JpaRepository<FeedingDailyRecord, Long> {

    FeedingDailyRecord findTopByPetProfileOrderByTimeDesc(final PetProfile petProfile);
}
