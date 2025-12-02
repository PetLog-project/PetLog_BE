package com.petlog.notification.scheduling;

import com.petlog.notification.service.NotificationService;
import com.petlog.pet.entity.FeedingDailyRecord;
import com.petlog.pet.entity.PetProfile;
import com.petlog.pet.entity.WateringDailyRecord;
import com.petlog.pet.repository.FeedingDailyRecordRepository;
import com.petlog.pet.repository.PetProfileRepository;
import com.petlog.pet.repository.WateringDailyRecordRepository;
import com.petlog.petgroup.entity.PetGroup;
import com.petlog.petgroup.entity.PetGroupMember;
import com.petlog.petgroup.repository.PetGroupMemberRepository;
import com.petlog.petgroup.repository.PetGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PetInfoNotification {

    private final NotificationService notificationService;
    private final PetProfileRepository petProfileRepository;
    private final PetGroupRepository petGroupRepository;
    private final PetGroupMemberRepository petGroupMemberRepository;
    private final FeedingDailyRecordRepository feedingDailyRecordRepository;
    private final WateringDailyRecordRepository wateringDailyRecordRepository;

    @Scheduled(cron = "0 0 */1 * * *")
    public void checkFeedingCycleAndSendNotification() {
        final List<PetGroup> petGroups = petGroupRepository.findAll();

        for(final PetGroup petGroup : petGroups) {
            final List<PetGroupMember> groupMembers = getPetGroupMembers(petGroup);

            if (groupMembers.isEmpty()) {
                continue;
            }

            final PetProfile petProfile = petProfileRepository.findByPetGroupId(petGroup.getId());
            final FeedingDailyRecord feedingDailyRecord = feedingDailyRecordRepository.findTopByPetProfileOrderByTimeDesc(petProfile);
            final WateringDailyRecord wateringDailyRecord = wateringDailyRecordRepository.findTopByPetProfileOrderByTimeDesc(petProfile);

            final long hoursSinceLastFeeding = Duration
                .between(feedingDailyRecord.getTime(), LocalDateTime.now())
                .toHours();

            final long hoursSinceLastWatering = Duration
                .between(wateringDailyRecord.getTime(), LocalDateTime.now())
                .toHours();

            if(hoursSinceLastFeeding >= petProfile.getFeedingCycle()) {
                sendFeedingNotificationToAllGroupMembers(groupMembers, petProfile, hoursSinceLastFeeding);
            }

            if(hoursSinceLastWatering >= petProfile.getWateringCycle()) {
                sendWateringNotificationToAllGroupMembers(groupMembers, petProfile, hoursSinceLastWatering);
            }
        }
    }

    private List<PetGroupMember> getPetGroupMembers(final PetGroup petGroup) {
        return petGroupMemberRepository.findAllByPetGroup(petGroup)
            .orElse(List.of());
    }

    private void sendFeedingNotificationToAllGroupMembers(final List<PetGroupMember> groupMembers, final PetProfile petProfile, final Long hoursSinceLastFeeding) {
        for (final PetGroupMember groupMember : groupMembers) {
            final Long groupMemberId = groupMember.getMember().getId();

            notificationService.sendNotification(
                groupMemberId,
                petProfile.getName() + "이에게 밥을 줄 시간이에요!",
                "밥을 준 지 " + hoursSinceLastFeeding + "시간이 지났어요.",
                "FEEDING"
            );
        }
    }

    private void sendWateringNotificationToAllGroupMembers(final List<PetGroupMember> groupMembers, final PetProfile petProfile, final Long hoursSinceLastWatering) {
        for (final PetGroupMember groupMember : groupMembers) {
            final Long groupMemberId = groupMember.getMember().getId();

            notificationService.sendNotification(
                groupMemberId,
                petProfile.getName() + "이에게 물을 교체해 줄 시간이에요!",
                "물을 교체해 준 지 " + hoursSinceLastWatering + "시간이 지났어요.",
                "WATERING"
            );
        }
    }
}
