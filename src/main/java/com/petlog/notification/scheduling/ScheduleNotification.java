package com.petlog.notification.scheduling;

import com.petlog.notification.service.NotificationService;
import com.petlog.petgroup.entity.PetGroup;
import com.petlog.petgroup.entity.PetGroupMember;
import com.petlog.petgroup.repository.PetGroupMemberRepository;
import com.petlog.petgroup.repository.PetGroupRepository;
import com.petlog.schedule.entity.Schedule;
import com.petlog.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ScheduleNotification {

    private final NotificationService notificationService;
    private final PetGroupRepository petGroupRepository;
    private final PetGroupMemberRepository petGroupMemberRepository;
    private final ScheduleRepository scheduleRepository;

    @Scheduled(cron = "0 */1 * * * *")
    public void checkScheduleAndSendNotification() {
        final List<PetGroup> petGroups = petGroupRepository.findAll();

        for(final PetGroup petGroup : petGroups) {
            final List<PetGroupMember> groupMembers = getPetGroupMembers(petGroup);

            if(groupMembers.isEmpty()) {
                continue;
            }

            final LocalDateTime now = LocalDateTime.now();
            final LocalDateTime start = now.withSecond(0).withNano(0);
            final LocalDateTime end   = start.plusMinutes(1);
            final List<Schedule> schedules = scheduleRepository.findAllByPetGroupAndRemindAtBetween(petGroup, start, end);

            if(schedules.isEmpty()) {
                continue;
            }

            checkIsAllDayScheduleAndSendNotification(groupMembers, schedules);
        }
    }

    private void checkIsAllDayScheduleAndSendNotification(final List<PetGroupMember> groupMembers, final List<Schedule> schedules) {
        for(final Schedule schedule : schedules) {
            if(schedule.isAllDay()) {
                sendIsAllDayScheduleNotificationToAllGroupMembers(groupMembers, schedule);
            }

            if(!schedule.isAllDay()) {
                sendScheduleNotificationToAllGroupMembers(groupMembers, schedule);
            }
        }
    }

    private List<PetGroupMember> getPetGroupMembers(final PetGroup petGroup) {
        return petGroupMemberRepository.findAllByPetGroup(petGroup)
            .orElse(List.of());
    }

    private void sendIsAllDayScheduleNotificationToAllGroupMembers(final List<PetGroupMember> groupMembers, final Schedule schedule) {
        for (final PetGroupMember groupMember : groupMembers) {
            final Long groupMemberId = groupMember.getMember().getId();

            notificationService.sendNotification(
                groupMemberId,
                schedule.getTitle(),
               "하루종일",
                "SCHEDULE"
            );
        }
    }

    private void sendScheduleNotificationToAllGroupMembers(final List<PetGroupMember> groupMembers, final Schedule schedule) {
        for (final PetGroupMember groupMember : groupMembers) {
            final Long groupMemberId = groupMember.getMember().getId();

            notificationService.sendNotification(
                groupMemberId,
                schedule.getTitle(),
                schedule.getStartAt().toLocalTime() + " ~ " + schedule.getEndAt().toLocalTime(),
                "SCHEDULE"
            );
        }
    }
}
