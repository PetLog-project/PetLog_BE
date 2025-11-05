package com.petlog.docs;

import com.petlog.member.controller.dto.request.UpdateNotificationWhetherRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "회원 API")
public interface MemberControllerDocs {

    @ApiResponse(responseCode = "200", description = "알림 여부 설정에 성공했습니다.")
    @Operation(summary = "알림 수신 여부 설정 API")
    ResponseEntity<com.petlog.common.response.ApiResponse<Void>> updateNotificationWhether(UpdateNotificationWhetherRequestDto request);
}
