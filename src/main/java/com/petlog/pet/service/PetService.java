package com.petlog.pet.service;

import com.petlog.member.entity.Member;
import com.petlog.member.repository.MemberRepository;
import com.petlog.pet.entity.FeedingDailyRecord;
import com.petlog.pet.entity.PetProfile;
import com.petlog.pet.entity.PoopDailyRecord;
import com.petlog.pet.entity.WateringDailyRecord;
import com.petlog.pet.repository.FeedingDailyRecordRepository;
import com.petlog.pet.repository.PetProfileRepository;
import com.petlog.pet.repository.PoopDailyRecordRepository;
import com.petlog.pet.repository.WateringDailyRecordRepository;
import com.petlog.pet.service.dto.GetFeedingInfoDto;
import com.petlog.pet.service.dto.GetPetProfileDto;
import com.petlog.pet.service.dto.GetPoopInfoDto;
import com.petlog.pet.service.dto.GetWateringInfoDto;
import com.petlog.pet.service.dto.UpdatePetProfileDto;
import com.petlog.petgroup.entity.PetGroup;
import com.petlog.petgroup.entity.PetGroupMember;
import com.petlog.petgroup.repository.PetGroupMemberRepository;
import com.petlog.petgroup.repository.PetGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class PetService {

    private final PetProfileRepository petProfileRepository;
    private final MemberRepository memberRepository;
    private final PetGroupRepository petGroupRepository;
    private final PetGroupMemberRepository petGroupMemberRepository;
    private final FeedingDailyRecordRepository feedingDailyRecordRepository;
    private final WateringDailyRecordRepository wateringDailyRecordRepository;
    private final PoopDailyRecordRepository poopDailyRecordRepository;

    @Transactional(readOnly = true)
    public GetPetProfileDto getPetProfile(final Long memberId, final Long groupId) {
        final Member member = getMember(memberId);
        final PetGroup petGroup = getPetGroup(groupId);
        getPetGroupMember(member, petGroup);

        final PetProfile petProfile = petProfileRepository.findByPetGroupId(groupId);

        return new GetPetProfileDto(
            petProfile.getImageUrl(),
            petProfile.getName(),
            petProfile.getAge(),
            petProfile.getWeight(),
            petProfile.getGender()
        );
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
    public GetFeedingInfoDto getFeedingInfo(final Long memberId, final Long groupId) {
        final Member member = getMember(memberId);
        final PetGroup petGroup = getPetGroup(groupId);
        getPetGroupMember(member, petGroup);

        final PetProfile petProfile = petProfileRepository.findByPetGroupId(groupId);
        final FeedingDailyRecord feedingDailyRecord = feedingDailyRecordRepository.findTopByPetProfileOrderByTimeDesc(petProfile);

        return new GetFeedingInfoDto(
            petProfile.getFeedingCycle(),
            feedingDailyRecord.getTime(),
            feedingDailyRecord.getMember().getName(),
            feedingDailyRecord.getMemo()
        );
    }

    @Transactional(readOnly = true)
    public GetWateringInfoDto getWateringInfo(final Long memberId, final Long groupId) {
        final Member member = getMember(memberId);
        final PetGroup petGroup = getPetGroup(groupId);
        getPetGroupMember(member, petGroup);

        final PetProfile petProfile = petProfileRepository.findByPetGroupId(groupId);
        final WateringDailyRecord wateringDailyRecord = wateringDailyRecordRepository.findTopByPetProfileOrderByTimeDesc(petProfile);

        return new GetWateringInfoDto(
            petProfile.getWateringCycle(),
            wateringDailyRecord.getTime(),
            wateringDailyRecord.getMember().getName(),
            wateringDailyRecord.getMemo()
        );
    }

    @Transactional(readOnly = true)
    public GetPoopInfoDto getPoopInfo(final Long memberId, final Long groupId) {
        final Member member = getMember(memberId);
        final PetGroup petGroup = getPetGroup(groupId);
        getPetGroupMember(member, petGroup);

        final PetProfile petProfile = petProfileRepository.findByPetGroupId(groupId);
        final int todayPoopCount = getTodayPoopRecordCount(petProfile);
        final PoopDailyRecord poopDailyRecord = poopDailyRecordRepository.findTopByPetProfileOrderByTimeDesc(petProfile);

        return new GetPoopInfoDto(
            todayPoopCount,
            poopDailyRecord.getMember().getName(),
            poopDailyRecord.getMemo()
        );
    }

    private int getTodayPoopRecordCount(final PetProfile petProfile) {
        final LocalDate today = LocalDate.now();
        final LocalDateTime start = today.atStartOfDay();
        final LocalDateTime end = today.plusDays(1).atStartOfDay();

        return poopDailyRecordRepository.countByPetProfileAndTimeBetween(petProfile, start, end);
    }

    @Transactional
    public void updatePetProfile(final Long memberId, final Long groupId, final UpdatePetProfileDto dto) {
        final Member member = getMember(memberId);
        final PetGroup petGroup = getPetGroup(groupId);
        getPetGroupMember(member, petGroup);

        final PetProfile petProfile = petProfileRepository.findByPetGroupId(groupId);

        petProfile.updatePetProfile(
            dto.imageUrl(),
            dto.name(),
            dto.age(),
            dto.weight(),
            dto.gender()
        );
    }
}
