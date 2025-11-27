package com.petlog.common.config.jwt;

import com.petlog.member.entity.Member;
import com.petlog.member.repository.MemberRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JwtProperties jwtProperties;

    @DisplayName("유저 정보와 만료 기간을 전달해 토큰을 만들 수 있다.")
    @Test
    void whenGenerateAccessToken_thenSuccess() {
        // Given
        final Member testMember = memberRepository.save(
            new Member(
                "test",
                "test@gmail.com",
                "aaa"
            )
        );

        // When
        final String token = tokenProvider.generateAccessToken(testMember, Duration.ofDays(14));

        // Then
        final Long memberId = Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .get("id", Long.class);

        assertThat(memberId).isEqualTo(testMember.getId());
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
    }

    @DisplayName("만료된 토큰인 경우 유효성 검증에 실패한다.")
    @Test
    void givenTokenIsExpired_whenValidToken_thenFailure() {
        // Given
        final String token = JwtFactory.builder()
            .expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
            .build()
            .createToken(jwtProperties);

        // When
        final boolean result = tokenProvider.validToken(token);

        // Then
        assertThat(result).isFalse();
    }

    @DisplayName("유효한 토큰인 경우 유효성 검증에 성공한다.")
    @Test
    void givenTokenIsExpired_whenValidToken_thenSuccess() {
        // Given
        final String token = JwtFactory.withDefaultValues()
            .createToken(jwtProperties);

        // When
        final boolean result = tokenProvider.validToken(token);

        // Then
        assertThat(result).isTrue();
    }

    @DisplayName("토큰 기반으로 인증 정보를 가져올 수 있다.")
    @Test
    void givenMemberToken_whenGetAuthentication_thenSuccess() {
        // Given
        final String memberEmail = "test@gmail.com";
        final String token = JwtFactory.builder()
            .subject(memberEmail)
            .build()
            .createToken(jwtProperties);

        // When
        final Authentication authentication = tokenProvider.getAuthentication(token);

        // Then
        assertThat(((UserDetails) authentication.getPrincipal()).getUsername()).isEqualTo(memberEmail);
    }

    @DisplayName("토큰으로 memberId를 가져올 수 있다.")
    @Test
    void givenMemberToken_whenGetMemberId_thenSuccess() {
        // Given
        final Long memberId = 1L;
        final String token = JwtFactory.builder()
            .claims(Map.of("id", memberId))
            .build()
            .createToken(jwtProperties);

        // When
        final Long memberIdByToken = tokenProvider.getMemberId(token);

        // Then
        assertThat(memberIdByToken).isEqualTo(memberId);
    }
}