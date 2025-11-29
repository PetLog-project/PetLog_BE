package com.petlog.pet.repository;

import com.petlog.pet.entity.PetProfile;
import com.petlog.pet.entity.PoopDailyRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface PoopDailyRecordRepository extends JpaRepository<PoopDailyRecord, Long> {

    PoopDailyRecord findTopByPetProfileOrderByTimeDesc(final PetProfile petProfile);

    int countByPetProfileAndTimeBetween(
        final PetProfile petProfile,
        final LocalDateTime start,
        final LocalDateTime end
    );
}
