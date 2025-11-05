package com.petlog.pet.repository;

import com.petlog.pet.entity.PetProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetProfileRepository extends JpaRepository<PetProfile, Long> {
}
