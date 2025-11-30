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
import java.util.concurrent.ThreadLocalRandom;

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
            dto.weight(),
            dto.gender(),
            dto.feedingCycle(),
            dto.wateringCycle()
        );
        petProfileRepository.save(petProfile);

        final FeedingDailyRecord feedingDailyRecord = new FeedingDailyRecord(
            petProfile,
            member,
            null
        );
        feedingDailyRecordRepository.save(feedingDailyRecord);

        final WateringDailyRecord wateringDailyRecord = new WateringDailyRecord(
            petProfile,
            member,
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
        final PetGroupMember petGroupMember = getPetGroupMember(member, petGroup);

        if(petGroupMember.isGroupOwner()) {
            updatePetGroupOwner(petGroupMember, petGroup);
        }

        petGroupMemberRepository.deleteByMemberIdAndGroupId(memberId, groupId);
    }

    private PetGroup getPetGroup(final Long groupId) {
        return petGroupRepository.findById(groupId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다."));
    }

    private PetGroupMember getPetGroupMember(final Member member, final PetGroup petGroup) {
        return petGroupMemberRepository.findByMemberAndPetGroup(member, petGroup)
            .orElseThrow(() -> new IllegalArgumentException("그룹에 존재하지 않는 회원입니다."));
    }

    private void updatePetGroupOwner(final PetGroupMember currentOwner, final PetGroup petGroup) {
        List<PetGroupMember> petGroupMembers = getPetGroupMembers(petGroup);

        final List<PetGroupMember> candidates = petGroupMembers.stream()
            .filter(member -> !member.equals(currentOwner))
            .toList();

        if (candidates.isEmpty()) {
            return;
        }

        final int index = ThreadLocalRandom.current().nextInt(candidates.size());
        final PetGroupMember newOwner = candidates.get(index);

        newOwner.updateIsGroupOwner(true);
    }

    private List<PetGroupMember> getPetGroupMembers(final PetGroup petGroup) {
        return petGroupMemberRepository.findAllByPetGroup(petGroup)
            .orElseThrow(() -> new IllegalArgumentException("해당 그룹에는 회원이 존재하지 않습니다."));
    }

    @Transactional(readOnly = true)
    public GetJoinCodeDto getJoinCode(final Long memberId, final Long groupId) {
        final Member member = getMember(memberId);
        final PetGroup petGroup = getPetGroup(groupId);
        validateMemberInPetGroup(member, petGroup);

        return new GetJoinCodeDto(petGroup.getJoinCode());
    }

    private void validateMemberInPetGroup(final Member member, final PetGroup petGroup) {
        if(!petGroupMemberRepository.existsByMemberAndPetGroup(member, petGroup)) {
            throw new IllegalArgumentException("그룹에 존재하지 않는 회원입니다.");
        }
    }

    @Transactional(readOnly = true)
    public GetNoteDto getNote(final Long memberId, final Long groupId) {
        final Member member = getMember(memberId);
        final PetGroup petGroup = getPetGroup(groupId);
        validateMemberInPetGroup(member, petGroup);

        return new GetNoteDto(petGroup.getNote());
    }

    @Transactional
    public void updateNote(final Long memberId, final Long groupId, final String note) {
        final Member member = getMember(memberId);
        final PetGroup petGroup = getPetGroup(groupId);
        final PetGroupMember petGroupMember = getPetGroupMember(member, petGroup);

        validateMemberIsGroupOwner(petGroupMember);

        petGroup.updateNote(note);
    }

    private void validateMemberIsGroupOwner(final PetGroupMember petGroupMember) {
        if(!petGroupMember.isGroupOwner()) {
            throw new IllegalArgumentException("참고사항 수정은 그룹장만 할 수 있습니다.");
        }
    }
}
