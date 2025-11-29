package com.petlog.petgroup.entity;

import com.petlog.common.entity.BaseEntity;
import com.petlog.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pet_group_member")
@Entity
public class PetGroupMember extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, updatable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_group_id", nullable = false, updatable = false)
    private PetGroup petGroup;

    @Column(name = "is_group_owner", nullable = false)
    private boolean isGroupOwner;

    public PetGroupMember(
        final Member member,
        final PetGroup petGroup,
        final boolean isGroupOwner
    ) {
        this.member = member;
        this.petGroup = petGroup;
        this.isGroupOwner = isGroupOwner;
    }

    public void updateIsGroupOwner(final boolean isGroupOwner) {
        this.isGroupOwner = isGroupOwner;
    }
}
