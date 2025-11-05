package com.petlog.petgroup.repository;

import com.petlog.petgroup.entity.PetGroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupMemberRepository extends JpaRepository<PetGroupMember, Long> {
}
