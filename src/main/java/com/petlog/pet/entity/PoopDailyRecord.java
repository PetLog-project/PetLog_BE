package com.petlog.pet.entity;

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

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "poop_daily_record")
@Entity
public class PoopDailyRecord extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_profile_id", nullable = false, updatable = false)
    private PetProfile petProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checker_id", nullable = false, updatable = false)
    private Member member;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Column(name = "memo", length = 200, nullable = true)
    private String memo;

    public PoopDailyRecord(
        final PetProfile petProfile,
        final Member member,
        final String memo
    ) {
        this.petProfile = petProfile;
        this.member = member;
        this.time = LocalDateTime.now();
        this.memo = memo;
    }
}
