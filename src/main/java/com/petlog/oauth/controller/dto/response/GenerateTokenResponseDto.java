package com.petlog.oauth.controller.dto.response;

public record GenerateTokenResponseDto(

    String accessToken,
    String refreshToken

) {
}
