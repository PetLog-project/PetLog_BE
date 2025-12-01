package com.petlog.notification.sender;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.petlog.notification.sender.dto.FcmNotificationRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FcmNotificationSender {

    public void send(final FcmNotificationRequestDto request) {
        try {
            final String response = FirebaseMessaging.getInstance().send(request.convertFcmMessage());
            log.info("푸시 알림 전송 완료 - {}", response);
        }
        catch (final FirebaseMessagingException e) {
            log.error("푸시 알림 전송에 실패하였습니다.", e);
            handleFcmException(e.getMessage());
        }
    }

    private void handleFcmException(final String errorResponse) {
        if (checkInvalidFcmTokenResponse(errorResponse)) {
            throw new IllegalArgumentException("유효하지 않은 푸시 알림 토큰입니다.");
        }
    }

    private boolean checkInvalidFcmTokenResponse(final String errorResponse) {
        return errorResponse.contains("The registration token is not a valid FCM registration token");
    }
}
