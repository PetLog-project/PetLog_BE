package com.petlog.petgroup.repository;

import com.petlog.petgroup.entity.PetGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<PetGroup, Long> {
}
