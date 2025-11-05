package com.petlog.pet.repository;

import com.petlog.pet.entity.FeedingDailyRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedingDailyRecordRepository extends JpaRepository<FeedingDailyRecord, Long> {
}
