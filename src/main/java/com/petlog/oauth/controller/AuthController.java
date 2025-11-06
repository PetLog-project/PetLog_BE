package com.petlog.oauth.controller;

import com.petlog.common.response.ApiResponse;
import com.petlog.docs.AuthControllerDocs;
import com.petlog.oauth.controller.dto.request.GenerateTokenRequestDto;
import com.petlog.oauth.controller.dto.request.TokenRefreshRequestDto;
import com.petlog.oauth.controller.dto.response.GenerateTokenResponseDto;
import com.petlog.oauth.controller.dto.response.TokenRefreshResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.petlog.oauth.controller.AuthSuccessCode.GENERATE_TOKEN;
import static com.petlog.oauth.controller.AuthSuccessCode.TOKEN_REFRESH;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController implements AuthControllerDocs {

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
    public ResponseEntity<ApiResponse<TokenRefreshResponseDto>> refreshToken(
        @RequestBody final TokenRefreshRequestDto request
    ) {
        TokenRefreshResponseDto response = new TokenRefreshResponseDto("accessToken");

        return ResponseEntity.ok(
            ApiResponse.successWithData(TOKEN_REFRESH, response)
        );
    }
}
