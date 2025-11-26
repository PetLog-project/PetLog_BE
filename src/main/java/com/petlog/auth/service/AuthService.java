package com.petlog.auth.service;

import com.petlog.common.config.jwt.TokenProvider;
import com.petlog.member.entity.Member;
import com.petlog.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class AuthService {

    private static final int EXPIRED_AT = 2;

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final MemberService memberService;

    public String createNewAccessToken(final String refreshToken) {

        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 refreshToken 입니다.");
        }

        final Long memberId = refreshTokenService.getRefreshToken(refreshToken).getMember().getId();
        final Member member = memberService.getMember(memberId);

        return tokenProvider.generateToken(member, Duration.ofHours(EXPIRED_AT));
    }
}
