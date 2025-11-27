package com.petlog.auth.controller.dto.request;

public record GenerateTokenRequestDto(

    String providerId,
    String name,
    String email

) {
}
