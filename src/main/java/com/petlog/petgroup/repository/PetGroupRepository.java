package com.petlog.petgroup.repository;

import com.petlog.petgroup.entity.PetGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetGroupRepository extends JpaRepository<PetGroup, Long> {

    Optional<PetGroup> findByJoinCode(final String joinCode);
}
