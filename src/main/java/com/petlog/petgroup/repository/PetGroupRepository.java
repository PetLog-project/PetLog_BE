package com.petlog.petgroup.repository;

import com.petlog.petgroup.entity.PetGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetGroupRepository extends JpaRepository<PetGroup, Long> {

    PetGroup findByJoinCode(final String joinCode);
}
