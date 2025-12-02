package com.petlog.schedule.repository;

import com.petlog.petgroup.entity.PetGroup;
import com.petlog.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByPetGroupAndStartAtBetween(final PetGroup petGroup, final LocalDateTime start, final LocalDateTime end);

    List<Schedule> findAllByPetGroupAndRemindAtBetween(final PetGroup petGroup, final LocalDateTime start, final LocalDateTime end);
}
