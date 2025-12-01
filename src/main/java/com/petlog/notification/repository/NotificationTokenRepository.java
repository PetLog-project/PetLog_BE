package com.petlog.notification.repository;

import com.petlog.member.entity.Member;
import com.petlog.notification.entity.NotificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationTokenRepository extends JpaRepository<NotificationToken, Long> {

    List<NotificationToken> findAllByMember_Id(final Long memberId);

    void deleteAllByValue(final String value);

    boolean existsByValue(final String token);

    Optional<NotificationToken> findByMemberAndValue(final Member member, final String value);
}
