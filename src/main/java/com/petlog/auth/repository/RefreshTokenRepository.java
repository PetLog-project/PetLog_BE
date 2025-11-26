package com.petlog.auth.repository;

import com.petlog.auth.entity.RefreshToken;
import com.petlog.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByMember(final Member member);
    Optional<RefreshToken> findByRefreshToken(final String refreshToken);
}
