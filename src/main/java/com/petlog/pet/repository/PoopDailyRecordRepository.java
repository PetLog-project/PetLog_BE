package com.petlog.pet.repository;

import com.petlog.pet.entity.PoopDailyRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoopDailyRecordRepository extends JpaRepository<PoopDailyRecord, Long> {
}
