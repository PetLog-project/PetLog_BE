package com.petlog.schedule.service;

import com.petlog.member.entity.Member;
import com.petlog.member.repository.MemberRepository;
import com.petlog.petgroup.entity.PetGroup;
import com.petlog.petgroup.entity.PetGroupMember;
import com.petlog.petgroup.repository.PetGroupMemberRepository;
import com.petlog.petgroup.repository.PetGroupRepository;
import com.petlog.schedule.entity.Schedule;
import com.petlog.schedule.repository.ScheduleRepository;
import com.petlog.schedule.service.dto.CreateScheduleDto;
import com.petlog.schedule.service.dto.GetScheduleInfoDto;
import com.petlog.schedule.service.dto.UpdateScheduleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;
    private final PetGroupRepository petGroupRepository;
    private final PetGroupMemberRepository petGroupMemberRepository;

    @Transactional
    public void createSchedule(final Long memberId, final Long groupId, final CreateScheduleDto dto) {
        final Member member = getMember(memberId);
        final PetGroup petGroup = getPetGroup(groupId);
        getPetGroupMember(member, petGroup);

        final Schedule schedule = new Schedule(
            petGroup,
            member,
            dto.title(),
            dto.isAllDay(),
            dto.startTime(),
            dto.endTime(),
            dto.remindNotificationAt(),
            dto.tag(),
            dto.memo()
        );
        scheduleRepository.save(schedule);
    }

    private Member getMember(final Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    private PetGroup getPetGroup(final Long groupId) {
        return petGroupRepository.findById(groupId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다."));
    }

    private PetGroupMember getPetGroupMember(final Member member, final PetGroup petGroup) {
        return petGroupMemberRepository.findByMemberAndPetGroup(member, petGroup)
            .orElseThrow(() -> new IllegalArgumentException("그룹에 존재하지 않는 회원입니다."));
    }

    @Transactional(readOnly = true)
    public List<GetScheduleInfoDto> getMonthlySchedule(final Long memberId, final Long groupId, final YearMonth date) {
        final Member member = getMember(memberId);
        final PetGroup petGroup = getPetGroup(groupId);
        getPetGroupMember(member, petGroup);

        final LocalDateTime start = date.atDay(1).atStartOfDay();
        final LocalDateTime end = date.atEndOfMonth().atTime(23, 59, 59);

        final List<Schedule> schedules =
            scheduleRepository.findAllByPetGroupAndStartAtBetween(petGroup, start, end);

        return schedules.stream()
            .map(s -> new GetScheduleInfoDto(
                s.getId(),
                s.getTitle(),
                s.isAllDay(),
                s.getStartAt(),
                s.getEndAt(),
                s.getType(),
                s.getRemindAt(),
                s.getMemo()
            ))
            .toList();
    }

    @Transactional
    public void updateSchedule(final Long memberId, final Long groupId, final Long scheduleId, final UpdateScheduleDto dto) {
        final Member member = getMember(memberId);
        final PetGroup petGroup = getPetGroup(groupId);
        getPetGroupMember(member, petGroup);

        final Schedule schedule = getSchedule(scheduleId);
        validateIsScheduleWriter(member, schedule);

        schedule.update(
            dto.title(),
            dto.isAllDay(),
            dto.startTime(),
            dto.endTime(),
            dto.remindNotificationAt(),
            dto.tag(),
            dto.memo()
        );
    }

    private Schedule getSchedule(final Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일정입니다."));
    }

    private void validateIsScheduleWriter(final Member member, final Schedule schedule) {
        if(!schedule.getMember().equals(member)) {
            throw new IllegalArgumentException("일정 작성자만 수정할 수 있습니다.");
        }
    }

    @Transactional
    public void deleteSchedule(final Long memberId, final Long groupId, final Long scheduleId) {
        final Member member = getMember(memberId);
        final PetGroup petGroup = getPetGroup(groupId);
        getPetGroupMember(member, petGroup);

        final Schedule schedule = getSchedule(scheduleId);
        validateIsScheduleWriter(member, schedule);

        scheduleRepository.deleteById(scheduleId);
    }
}
