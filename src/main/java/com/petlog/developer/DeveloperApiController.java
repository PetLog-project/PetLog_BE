package com.petlog.developer;

import com.petlog.auth.service.AuthService;
import com.petlog.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.petlog.auth.controller.AuthSuccessCode.GENERATE_TOKEN;

@Profile("local")
@RequiredArgsConstructor
@RestController
public class DeveloperApiController {

    private final AuthService authService;

    @PostMapping("/api/auth/{memberId}")
    public ResponseEntity<ApiResponse<String>> generateTokenLocal(
        @PathVariable Long memberId
    ) {
        final String token = authService.generateAccessToken(memberId);

        return ResponseEntity.ok(
            ApiResponse.successWithData(GENERATE_TOKEN, token)
        );
    }
}
