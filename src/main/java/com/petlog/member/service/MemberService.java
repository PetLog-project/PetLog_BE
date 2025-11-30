package com.petlog.member.service;

import com.petlog.member.entity.Member;
import com.petlog.member.repository.MemberRepository;
import com.petlog.member.service.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member login(final LoginDto dto) {
        final Member member = memberRepository.findByProviderId(dto.providerId());

        if(member == null) {
            return signUp(dto.name(), dto.email(), dto.providerId());
        }

        return member;
    }

    private Member signUp(final String name, final String email, final String providerId) {
        return memberRepository.save(new Member(name, email, providerId));
    }

    @Transactional(readOnly = true)
    public boolean getIsNotificationEnabled(final Long memberId) {
        final Member member = getMember(memberId);

        return member.isNotificationEnabled();
    }

    private Member getMember(final Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    @Transactional
    public void updateIsNotificationEnabled(final Long memberId, final boolean isNotificationEnabled) {
        final Member member = getMember(memberId);
        member.updateIsNotificationEnabled(isNotificationEnabled);
    }
}
