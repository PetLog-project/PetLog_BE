package com.petlog.member.controller;

import com.petlog.common.response.ApiResponse;
import com.petlog.docs.MemberControllerDocs;
import com.petlog.member.controller.dto.request.UpdateIsNotificationEnabledRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.petlog.member.controller.MemberSuccessCode.UPDATE_IS_NOTIFICATION_ENABLED;
import static com.petlog.member.controller.MemberSuccessCode.WITHDRAW;

@RequiredArgsConstructor
@RestController
public class MemberController implements MemberControllerDocs {

    @PutMapping("/api/notification")
    public ResponseEntity<ApiResponse<Void>> updateIsNotificationEnabled(
        @RequestBody final UpdateIsNotificationEnabledRequestDto request
    ) {
        return ResponseEntity.ok(
            ApiResponse.success(UPDATE_IS_NOTIFICATION_ENABLED)
        );
    }

    @DeleteMapping("/api/withdraw")
    public ResponseEntity<ApiResponse<Void>> withdraw() {
        return ResponseEntity.ok(
            ApiResponse.success(WITHDRAW)
        );
    }
}
