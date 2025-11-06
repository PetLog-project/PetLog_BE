package com.petlog.docs;

import com.petlog.common.response.ApiResponse;
import com.petlog.oauth.controller.dto.request.GenerateTokenRequestDto;
import com.petlog.oauth.controller.dto.request.TokenRefreshRequestDto;
import com.petlog.oauth.controller.dto.response.GenerateTokenResponseDto;
import com.petlog.oauth.controller.dto.response.TokenRefreshResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Auth API")
public interface AuthControllerDocs {

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "JWT access token 발급에 성공하였습니다.")
    @Operation(summary = "OAuth 카카오 로그인 API")
    ResponseEntity<ApiResponse<GenerateTokenResponseDto>> generateToken(@RequestBody final GenerateTokenRequestDto request);

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "JWT access token 재발급에 성공하였습니다.")
    @Operation(summary = "JWT access token 재발급 API")
    ResponseEntity<ApiResponse<TokenRefreshResponseDto>> refreshToken(@RequestBody final TokenRefreshRequestDto request);
}
