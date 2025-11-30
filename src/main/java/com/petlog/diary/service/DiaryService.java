package com.petlog.diary.service;

import com.petlog.diary.entity.Diary;
import com.petlog.diary.entity.DiaryImage;
import com.petlog.diary.repository.DiaryImageRepository;
import com.petlog.diary.repository.DiaryRepository;
import com.petlog.diary.service.dto.CreateDiaryDto;
import com.petlog.diary.service.dto.GetDiaryDto;
import com.petlog.diary.service.dto.GetDiaryInfoDto;
import com.petlog.member.entity.Member;
import com.petlog.member.repository.MemberRepository;
import com.petlog.petgroup.entity.PetGroup;
import com.petlog.petgroup.entity.PetGroupMember;
import com.petlog.petgroup.repository.PetGroupMemberRepository;
import com.petlog.petgroup.repository.PetGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final DiaryImageRepository diaryImageRepository;
    private final MemberRepository memberRepository;
    private final PetGroupRepository petGroupRepository;
    private final PetGroupMemberRepository petGroupMemberRepository;

    @Transactional
    public void createDiary(final Long memberId, final Long groupId, final CreateDiaryDto dto) {
        final Member member = getMember(memberId);
        final PetGroup petGroup = getPetGroup(groupId);
        getPetGroupMember(member, petGroup);

        final Diary diary = new Diary(
            petGroup,
            member,
            dto.title(),
            dto.content(),
            dto.writtenAt()
        );
        diaryRepository.save(diary);

        final List<DiaryImage> images = dto.images().stream()
            .map(img -> new DiaryImage(diary, img))
            .toList();
        diaryImageRepository.saveAll(images);
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
    public List<GetDiaryInfoDto> getAllDiaries(final Long memberId, final Long groupId) {
        final Member member = getMember(memberId);
        final PetGroup petGroup = getPetGroup(groupId);
        getPetGroupMember(member, petGroup);

        final List<Diary> diaries = diaryRepository.findAllByPetGroup(petGroup);

        return diaries.stream()
            .map(info -> new GetDiaryInfoDto(
                info.getId(),
                info.getTitle(),
                info.getImages().stream()
                    .findFirst()
                    .map(DiaryImage::getImageUrl)
                    .orElse(null),
                info.getWrittenAt()
            ))
            .toList();
    }

    @Transactional(readOnly = true)
    public GetDiaryDto getDiary(final Long memberId, final Long groupId, final Long diaryId) {
        final Member member = getMember(memberId);
        final PetGroup petGroup = getPetGroup(groupId);
        getPetGroupMember(member, petGroup);

        final Diary diary = getDiaryDetail(diaryId);

        return new GetDiaryDto(
            diary.getTitle(),
            diary.getContent(),
            diary.getImages().stream()
                    .map(DiaryImage::getImageUrl)
                    .toList(),
            diary.getWrittenAt(),
            diary.getMember().getName()
        );
    }

    private Diary getDiaryDetail(final Long diaryId) {
        return diaryRepository.findById(diaryId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일기입니다."));
    }
}
