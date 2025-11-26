package com.petlog.auth.controller.dto.response;

public record GenerateTokenResponseDto(

    String accessToken,
    String refreshToken

) {
}
