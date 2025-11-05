package com.petlog.member.controller;

import com.petlog.common.response.ApiResponse;
import com.petlog.docs.MemberControllerDocs;
import com.petlog.member.controller.dto.request.UpdateNotificationWhetherRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.petlog.member.controller.MemberSuccessCode.UPDATE_NOTIFICATION_WHETHER;

@RequiredArgsConstructor
@RestController
public class MemberController implements MemberControllerDocs {

    @PutMapping("/api/notification")
    public ResponseEntity<ApiResponse<Void>> updateNotificationWhether(
        @RequestBody UpdateNotificationWhetherRequestDto request
    ) {
        return ResponseEntity.ok(
            ApiResponse.success(UPDATE_NOTIFICATION_WHETHER)
        );
    }
}
