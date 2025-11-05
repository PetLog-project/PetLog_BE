package com.petlog.pet.repository;

import com.petlog.pet.entity.WateringDailyRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WateringDailyRecordRepository extends JpaRepository<WateringDailyRecord, Long> {
}
