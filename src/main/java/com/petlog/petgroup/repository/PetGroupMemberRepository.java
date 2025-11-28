package com.petlog.petgroup.repository;

import com.petlog.petgroup.entity.PetGroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetGroupMemberRepository extends JpaRepository<PetGroupMember, Long> {

    @Query("""
        SELECT pgm.petGroup.id
        FROM PetGroupMember pgm
        WHERE pgm.member.id = :memberId
    """)
    List<Long> findPetGroupIdsByMemberId(final Long memberId);
}
