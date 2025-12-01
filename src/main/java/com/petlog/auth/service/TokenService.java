package com.petlog.auth.service;

import com.petlog.auth.entity.RefreshToken;
import com.petlog.auth.repository.RefreshTokenRepository;
import com.petlog.auth.jwt.TokenProvider;
import com.petlog.member.entity.Member;
import com.petlog.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Transactional
@RequiredArgsConstructor
@Service
public class TokenService {

    private static final int EXPIRED_AT_HOURS = 2;
    private static final int ONE_YEAR_HOURS = 24 * 365;
    private static final int ONE_HOUR = 1;
    private static final int ONE_MONTH_HOURS = 24 * 30;

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    @Profile("local")
    public String generateLocalAccessToken(final Long memberId) {
        final Member member = getMember(memberId);

        return tokenProvider.generateAccessToken(member, Duration.ofHours(ONE_YEAR_HOURS));
    }

    private Member getMember(final Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    @Transactional(readOnly = true)
    public String generateAccessToken(final Long memberId) {
        final Member member = getMember(memberId);

        return tokenProvider.generateAccessToken(member, Duration.ofHours(ONE_HOUR));
    }

    @Transactional
    public String generateRefreshToken(final Long memberId) {
        final Member member = getMember(memberId);

        final String refreshToken = tokenProvider.generateRefreshToken(member, Duration.ofHours(ONE_MONTH_HOURS));
        refreshTokenRepository.save(new RefreshToken(member, refreshToken));

        return refreshToken;
    }

    @Transactional(readOnly = true)
    public String reissueAccessToken(final String refreshToken) {

        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 refreshToken 입니다.");
        }

        final Long memberId = getRefreshToken(refreshToken).getMember().getId();
        final Member member = getMember(memberId);

        return tokenProvider.generateAccessToken(member, Duration.ofHours(EXPIRED_AT_HOURS));
    }

    private RefreshToken getRefreshToken(final String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 refreshToken 입니다."));
    }
}