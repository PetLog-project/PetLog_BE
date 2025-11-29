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
import com.petlog.petgroup.entity.PetGroupMember;
import com.petlog.petgroup.generator.PetGroupJoinCodeGenerator;
import com.petlog.petgroup.generator.RandomJoinCodeGenerator;
import com.petlog.petgroup.repository.PetGroupMemberRepository;
import com.petlog.petgroup.repository.PetGroupRepository;
import com.petlog.petgroup.service.dto.CreatePetGroupDto;
import com.petlog.petgroup.service.dto.GetJoinCodeDto;
import com.petlog.petgroup.service.dto.GetMyPetGroupDto;
import com.petlog.petgroup.service.dto.GetNoteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PetGroupService {

    private final MemberRepository memberRepository;
    private final PetGroupRepository petGroupRepository;
    private final PetGroupMemberRepository petGroupMemberRepository;
    private final PetProfileRepository petProfileRepository;
    private final FeedingDailyRecordRepository feedingDailyRecordRepository;
    private final WateringDailyRecordRepository wateringDailyRecordRepository;

    @Transactional
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

        final PetGroupMember petGroupMember = new PetGroupMember(member, petGroup, true);
        petGroupMemberRepository.save(petGroupMember);
    }

    private Member getMember(final Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    @Transactional
    public void joinPetGroup(final Long memberId, final String joinCode) {
        final Member member = getMember(memberId);
        final PetGroup petGroup = getPetGroupByJoinCode(joinCode);
        validateAlreadyMemberInPetGroup(member, petGroup);

        final PetGroupMember petGroupMember = new PetGroupMember(member, petGroup, false);
        petGroupMemberRepository.save(petGroupMember);
    }

    private PetGroup getPetGroupByJoinCode(final String joinCode) {
        return petGroupRepository.findByJoinCode(joinCode)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다."));
    }

    private void validateAlreadyMemberInPetGroup(final Member member, final PetGroup petGroup) {
        if(petGroupMemberRepository.existsByMemberAndPetGroup(member, petGroup)) {
            throw new IllegalArgumentException("이미 그룹에 참여중인 회원입니다.");
        }
    }

    @Transactional(readOnly = true)
    public GetMyPetGroupDto getMyPetGroups(final Long memberId) {
        getMember(memberId);
        final List<Long> groupIds = petGroupMemberRepository.findPetGroupIdsByMemberId(memberId);
        return new GetMyPetGroupDto(groupIds);
    }

    @Transactional
    public void leavePetGroup(final Long memberId, final Long groupId) {
        final Member member = getMember(memberId);
        final PetGroup petGroup = getPetGroup(groupId);
        validateMemberInPetGroup(member, petGroup);

        petGroupMemberRepository.deleteByMemberIdAndGroupId(memberId, groupId);
    }

    private PetGroup getPetGroup(final Long petGroupId) {
        return petGroupRepository.findById(petGroupId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다."));
    }

    private void validateMemberInPetGroup(final Member member, final PetGroup petGroup) {
        if(!petGroupMemberRepository.existsByMemberAndPetGroup(member, petGroup)) {
            throw new IllegalArgumentException("그룹에 존재하지 않는 회원입니다.");
        }
    }

    @Transactional(readOnly = true)
    public GetJoinCodeDto getJoinCode(final Long memberId, final Long groupId) {
        final Member member = getMember(memberId);
        final PetGroup petGroup = getPetGroup(groupId);
        validateMemberInPetGroup(member, petGroup);

        return new GetJoinCodeDto(petGroup.getJoinCode());
    }

    @Transactional(readOnly = true)
    public GetNoteDto getNote(final Long memberId, final Long groupId) {
        final Member member = getMember(memberId);
        final PetGroup petGroup = getPetGroup(groupId);
        validateMemberInPetGroup(member, petGroup);

        return new GetNoteDto(petGroup.getNote());
    }
}
