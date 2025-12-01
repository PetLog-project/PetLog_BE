package com.petlog.notification.controller;

import com.petlog.auth.resolver.Authenticated;
import com.petlog.common.response.ApiResponse;
import com.petlog.docs.NotificationControllerDocs;
import com.petlog.notification.controller.dto.request.DeleteNotificationTokenRequestDto;
import com.petlog.notification.controller.dto.request.SaveNotificationTokenRequestDto;
import com.petlog.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.petlog.notification.controller.NotificationSuccessCode.DELETE_NOTIFICATION_TOKEN;
import static com.petlog.notification.controller.NotificationSuccessCode.SAVE_NOTIFICATION_TOKEN;

@RequiredArgsConstructor
@RequestMapping("api/notification")
@RestController
public class NotificationController implements NotificationControllerDocs {

    private final NotificationService notificationService;

    @PostMapping("/token")
    public ResponseEntity<ApiResponse<Void>> saveNotificationToken(
        @Authenticated final Long memberId,
        @RequestBody final SaveNotificationTokenRequestDto request
    ) {
        notificationService.saveNotificationToken(memberId, request.token());

        return ResponseEntity.ok(
            ApiResponse.success(SAVE_NOTIFICATION_TOKEN)
        );
    }

    @DeleteMapping("/token")
    public ResponseEntity<ApiResponse<Void>> deleteNotificationToken(
        @Authenticated final Long memberId,
        @RequestBody final DeleteNotificationTokenRequestDto request
    ) {
        notificationService.deleteNotificationToken(memberId, request.token());

        return ResponseEntity.ok(
            ApiResponse.success(DELETE_NOTIFICATION_TOKEN)
        );
    }
}
