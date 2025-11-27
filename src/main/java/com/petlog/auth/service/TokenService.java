package com.petlog.auth.service;

import com.petlog.auth.entity.RefreshToken;
import com.petlog.auth.repository.RefreshTokenRepository;
import com.petlog.common.config.jwt.TokenProvider;
import com.petlog.member.entity.Member;
import com.petlog.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private static final int EXPIRED_AT = 2;

    private final TokenProvider tokenProvider;
    private final MemberService memberService;
    private final RefreshTokenRepository refreshTokenRepository;

    @Profile("local")
    public String generateLocalAccessToken(final Long memberId) {
        final Member member = memberService.getMember(memberId);

        return tokenProvider.generateAccessToken(member, Duration.ofHours(8760));
    }

    public String generateAccessToken(final Long memberId) {
        final Member member = memberService.getMember(memberId);

        return tokenProvider.generateAccessToken(member, Duration.ofHours(1));
    }

    public String generateRefreshToken(final Long memberId) {
        final Member member = memberService.getMember(memberId);

        final String refreshToken = tokenProvider.generateRefreshToken(member, Duration.ofHours(720));
        refreshTokenRepository.save(new RefreshToken(member, refreshToken));

        return refreshToken;
    }

    public String reissueAccessToken(final String refreshToken) {

        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 refreshToken 입니다.");
        }

        final Long memberId = getRefreshToken(refreshToken).getMember().getId();
        final Member member = memberService.getMember(memberId);

        return tokenProvider.generateAccessToken(member, Duration.ofHours(EXPIRED_AT));
    }

    private RefreshToken getRefreshToken(final String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 refreshToken 입니다."));
    }
}