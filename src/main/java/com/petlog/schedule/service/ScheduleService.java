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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
