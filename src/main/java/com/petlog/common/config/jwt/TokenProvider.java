package com.petlog.common.config.jwt;

import com.petlog.member.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;

    public String generateAccessToken(final Member member, final Duration expiredAt) {
        final Date now = new Date();

        return makeToken(
            member,
            new Date(now.getTime() + expiredAt.toMillis()),
            TokenType.ACCESS_TOKEN
        );
    }

    private String makeToken(
        final Member member,
        final Date expiry,
        final TokenType tokenType
    ) {
        final Date now = new Date();

        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setIssuer(jwtProperties.getIssuer())
            .setIssuedAt(now)
            .setExpiration(expiry)
            .setSubject(member.getEmail())
            .claim("id", member.getId())
            .claim("type", tokenType)
            .signWith(getSigningKey())
            .compact();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
    }

    public String generateRefreshToken(final Member member, final Duration expiredAt) {
        final Date now = new Date();

        return makeToken(
            member,
            new Date(now.getTime() + expiredAt.toMillis()),
            TokenType.REFRESH_TOKEN
        );
    }

    public boolean validToken(final String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);

            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(final String token) {
        final Claims claims = getClaims(token);
        final Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(
            new org.springframework.security.core.userdetails.User(claims.getSubject(), "", authorities),
            token,
            authorities
        );
    }

    private Claims getClaims(final String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public Long getMemberId(final String token) {
        final Claims claims = getClaims(token);

        return claims.get("id", Long.class);
    }
}
