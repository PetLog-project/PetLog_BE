package com.petlog.docs;

import com.petlog.auth.resolver.Authenticated;
import com.petlog.common.response.ApiResponse;
import com.petlog.notification.controller.dto.request.DeleteNotificationTokenRequestDto;
import com.petlog.notification.controller.dto.request.SaveNotificationTokenRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "푸시 알림 API")
public interface NotificationControllerDocs {

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "푸시 알림 토큰 저장에 성공하였습니다.")
    @Operation(summary = "푸시 알림 토큰 저장 API")
    ResponseEntity<ApiResponse<Void>> saveNotificationToken(@Authenticated final Long memberId, @RequestBody final SaveNotificationTokenRequestDto request);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "푸시 알림 토큰 삭제에 성공하였습니다.")
    @Operation(summary = "푸시 알림 토큰 삭제 API")
    ResponseEntity<ApiResponse<Void>> deleteNotificationToken(@Authenticated final Long memberId, @RequestBody final DeleteNotificationTokenRequestDto request);
}
