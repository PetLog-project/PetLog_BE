package com.petlog.notification.service;

import com.petlog.member.entity.Member;
import com.petlog.member.repository.MemberRepository;
import com.petlog.notification.entity.NotificationToken;
import com.petlog.notification.repository.NotificationTokenRepository;
import com.petlog.notification.sender.FcmNotificationSender;
import com.petlog.notification.sender.dto.FcmNotificationRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {

    private final FcmNotificationSender fcmNotificationSender;
    private final NotificationTokenRepository notificationTokenRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void sendNotification(
        final Long addresseeId,
        final String title,
        final String body,
        final String type
    ) {
        notificationTokenRepository.findAllByMember_Id(addresseeId).forEach(
            notificationToken -> sendNotification(notificationToken, title, body, type));
    }

    private void sendNotification(
        final NotificationToken notificationToken,
        final String title,
        final String body,
        final String type
    ) {
        try {
            final FcmNotificationRequestDto fcmNotificationRequest = new FcmNotificationRequestDto(notificationToken.getValue(), title, body, type);
            fcmNotificationSender.send(fcmNotificationRequest);
        }
        catch (final IllegalArgumentException e) {
            notificationTokenRepository.deleteAllByValue(notificationToken.getValue());
            log.info("유효하지 않은 토큰 제거 - {}", notificationToken.getValue());
        }
    }

    @Transactional
    public void saveNotificationToken(final Long memberId, final String notificationToken) {
        validateNotificationTokenIsNullOrEmpty(notificationToken);

        final Member member = getMember(memberId);
        if (notificationTokenRepository.existsByValue(notificationToken)) {
            log.trace("이미 존재하는 푸시 알림 토큰입니다.");
            return;
        }

        final NotificationToken notificationTokenJpaEntity = new NotificationToken(member, notificationToken);
        notificationTokenRepository.save(notificationTokenJpaEntity);
        log.trace("푸시 알림 토큰 저장 - {}", notificationToken);
    }

    private void validateNotificationTokenIsNullOrEmpty(final String token) {
        if (token == null || token.isEmpty()) {
            log.info("입력된 알림 토큰 값이 null 혹은 공백 - {}", token);
            throw new IllegalArgumentException("알림 토큰 값은 공백일 수 없습니다.");
        }
    }

    private Member getMember(final Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    @Transactional
    public void deleteNotificationToken(final Long memberId, final String notificationToken) {
        validateNotificationTokenIsNullOrEmpty(notificationToken);

        final Member member = getMember(memberId);
        notificationTokenRepository.findByMemberAndValue(member, notificationToken)
            .ifPresent(notificationTokenJpaEntity -> {
                notificationTokenRepository.delete(notificationTokenJpaEntity);
                log.info("푸시 알림 토큰 제거 - {}", notificationToken);});
    }
}
