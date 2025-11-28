package com.petlog.petgroup.repository;

import com.petlog.member.entity.Member;
import com.petlog.petgroup.entity.PetGroup;
import com.petlog.petgroup.entity.PetGroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetGroupMemberRepository extends JpaRepository<PetGroupMember, Long> {

    @Query("""
        SELECT pgm.petGroup.id
        FROM PetGroupMember pgm
        WHERE pgm.member.id = :memberId
    """)
    List<Long> findPetGroupIdsByMemberId(final Long memberId);

    @Modifying
    @Query("""
        DELETE FROM PetGroupMember pgm
        WHERE pgm.member.id = :memberId
        AND pgm.petGroup.id = :groupId
    """)
    void deleteByMemberIdAndGroupId(final Long memberId, final Long groupId);

    boolean existsByMemberAndPetGroup(final Member member, final PetGroup group);
}
