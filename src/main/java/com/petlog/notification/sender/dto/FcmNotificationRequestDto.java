package com.petlog.notification.sender.dto;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

public record FcmNotificationRequestDto(

    String fcmToken,
    String title,
    String body,
    String type

) {

    public Message convertFcmMessage() {
        return Message.builder()
            .setNotification(createNotification())
            .setToken(fcmToken)
            .setApnsConfig(createApnsConfig())
            .setAndroidConfig(createAndroidConfig())
            .putData("type", type)
            .build();
    }

    private ApnsConfig createApnsConfig() {
        return ApnsConfig.builder()
            .setAps(
                Aps.builder()
                    .setContentAvailable(true)
                    .build())
            .build();
    }

    private static AndroidConfig createAndroidConfig() {
        return AndroidConfig.builder()
            .setPriority(AndroidConfig.Priority.HIGH)
            .build();
    }

    private Notification createNotification() {
        return Notification.builder()
            .setTitle(title)
            .setBody(body)
            .build();
    }
}
