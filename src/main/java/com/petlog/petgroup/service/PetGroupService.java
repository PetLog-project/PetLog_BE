package com.petlog.petgroup.service;

import com.petlog.member.entity.Member;
import com.petlog.member.repository.MemberRepository;
import com.petlog.pet.entity.FeedingDailyRecord;
import com.petlog.pet.entity.PetProfile;
import com.petlog.pet.entity.WateringDailyRecord;
import com.petlog.pet.repository.FeedingDailyRecordRepository;
import com.petlog.pet.repository.PetProfileRepository;
import com.petlog.pet.repository.WateringDailyRecordRepository;
import com.petlog.petgroup.entity.PetGroup;
import com.petlog.petgroup.generator.PetGroupJoinCodeGenerator;
import com.petlog.petgroup.generator.RandomJoinCodeGenerator;
import com.petlog.petgroup.repository.PetGroupRepository;
import com.petlog.petgroup.service.dto.CreatePetGroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PetGroupService {

    private final MemberRepository memberRepository;
    private final PetGroupRepository petGroupRepository;
    private final PetProfileRepository petProfileRepository;
    private final FeedingDailyRecordRepository feedingDailyRecordRepository;
    private final WateringDailyRecordRepository wateringDailyRecordRepository;

    public void createPetGroup(final Long memberId, final CreatePetGroupDto dto) {
        final Member member = getMember(memberId);

        final RandomJoinCodeGenerator generator = new PetGroupJoinCodeGenerator();
        final String joinCode = generator.generate();

        final PetGroup petGroup = new PetGroup(joinCode, dto.note());
        petGroupRepository.save(petGroup);

        final PetProfile petProfile = new PetProfile(
            petGroup,
            dto.imageUrl(),
            dto.name(),
            dto.age(),
            dto.gender(),
            dto.weight(),
            dto.feedingCycle(),
            dto.wateringCycle()
        );
        petProfileRepository.save(petProfile);

        final FeedingDailyRecord feedingDailyRecord = new FeedingDailyRecord(
            petProfile,
            member,
            dto.lastFeedingTime(),
            null
        );
        feedingDailyRecordRepository.save(feedingDailyRecord);

        final WateringDailyRecord wateringDailyRecord = new WateringDailyRecord(
            petProfile,
            member,
            dto.lastWateringTime(),
            null
        );
        wateringDailyRecordRepository.save(wateringDailyRecord);
    }

    private Member getMember(final Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }
}
