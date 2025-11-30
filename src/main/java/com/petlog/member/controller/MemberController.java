package com.petlog.member.controller;

import com.petlog.auth.resolver.Authenticated;
import com.petlog.common.response.ApiResponse;
import com.petlog.docs.MemberControllerDocs;
import com.petlog.member.controller.dto.request.UpdateIsNotificationEnabledRequestDto;
import com.petlog.member.controller.dto.response.GetIsNotificationEnabledResponseDto;
import com.petlog.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.petlog.member.controller.MemberSuccessCode.GET_IS_NOTIFICATION_ENABLED;
import static com.petlog.member.controller.MemberSuccessCode.UPDATE_IS_NOTIFICATION_ENABLED;
import static com.petlog.member.controller.MemberSuccessCode.WITHDRAW;

@RequiredArgsConstructor
@RestController
public class MemberController implements MemberControllerDocs {

    private final MemberService memberService;

    @GetMapping("/api/notification")
    public ResponseEntity<ApiResponse<GetIsNotificationEnabledResponseDto>> getIsNotificationEnabled(
        @Authenticated final Long memberId
    ) {
        final boolean isNotificationEnabled = memberService.getIsNotificationEnabled(memberId);
        final GetIsNotificationEnabledResponseDto response = new GetIsNotificationEnabledResponseDto(isNotificationEnabled);

        return ResponseEntity.ok(
            ApiResponse.successWithData(GET_IS_NOTIFICATION_ENABLED, response)
        );
    }

    @PutMapping("/api/notification")
    public ResponseEntity<ApiResponse<Void>> updateIsNotificationEnabled(
        @Authenticated final Long memberId,
        @RequestBody final UpdateIsNotificationEnabledRequestDto request
    ) {
        memberService.updateIsNotificationEnabled(memberId, request.isNotificationEnabled());

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
