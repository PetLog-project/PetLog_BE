package com.petlog.schedule.entity;

import com.petlog.common.entity.BaseEntity;
import com.petlog.petgroup.entity.PetGroup;
import com.petlog.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "schedule")
@Entity
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_group_id", nullable = false, updatable = false)
    private PetGroup petGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = false, updatable = false)
    private Member member;

    @Column(name = "title", length = 20, nullable = false)
    private String title;

    @Column(name = "is_all_day", nullable = false)
    private boolean isAllDay;

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;

    @Column(name = "remind_at", nullable = false)
    private LocalDateTime remindAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 10, nullable = false)
    private ScheduleType type;

    @Column(name = "memo", length = 400, nullable = true)
    private String memo;

    public Schedule(
        final PetGroup petGroup,
        final Member member,
        final String title,
        final boolean isAllDay,
        final LocalDateTime startAt,
        final LocalDateTime endAt,
        final LocalDateTime remindAt,
        final ScheduleType type,
        final String memo
    ) {
        this.petGroup = petGroup;
        this.member = member;
        this.title = title;
        this.isAllDay = isAllDay;
        this.startAt = startAt;
        this.endAt = endAt;
        this.remindAt = remindAt;
        this.type = type;
        this.memo = memo;
    }

    public void update(
        final String title,
        final boolean isAllDay,
        final LocalDateTime startTime,
        final LocalDateTime endTime,
        final LocalDateTime remindNotificationAt,
        final ScheduleType tag,
        final String memo
    ) {
        this.title = title;
        this.isAllDay = isAllDay;
        this.startAt = startTime;
        this.endAt = endTime;
        this.remindAt = remindNotificationAt;
        this.type = tag;
        this.memo = memo;
    }
}
