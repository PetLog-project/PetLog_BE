package com.petlog.auth.controller;

import com.petlog.auth.controller.dto.request.GenerateTokenRequestDto;
import com.petlog.auth.controller.dto.request.TokenRefreshRequestDto;
import com.petlog.auth.controller.dto.response.GenerateTokenResponseDto;
import com.petlog.auth.controller.dto.response.TokenRefreshResponseDto;
import com.petlog.auth.service.TokenService;
import com.petlog.common.response.ApiResponse;
import com.petlog.docs.AuthControllerDocs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.petlog.auth.controller.AuthSuccessCode.GENERATE_TOKEN;
import static com.petlog.auth.controller.AuthSuccessCode.TOKEN_REFRESH;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController implements AuthControllerDocs {

    private final TokenService tokenService;

    @PostMapping("/login/kakao")
    public ResponseEntity<ApiResponse<GenerateTokenResponseDto>> generateToken(
        @RequestBody final GenerateTokenRequestDto request
    ) {
        GenerateTokenResponseDto response = new GenerateTokenResponseDto("accessToken", "refreshToken");

        return ResponseEntity.ok(
            ApiResponse.successWithData(GENERATE_TOKEN, response)
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<TokenRefreshResponseDto>> generateNewAccessToken(
        @RequestBody final TokenRefreshRequestDto request
    ) {
        final String newAccessToken = tokenService.createNewAccessToken(request.refreshToken());
        final TokenRefreshResponseDto response = new TokenRefreshResponseDto(newAccessToken);

        return ResponseEntity.ok(
            ApiResponse.successWithData(TOKEN_REFRESH, response)
        );
    }
}
