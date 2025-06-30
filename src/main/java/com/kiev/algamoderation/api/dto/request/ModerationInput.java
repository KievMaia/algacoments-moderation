package com.kiev.algamoderation.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ModerationInput(
    @NotBlank String text,
    @NotBlank String commentId
) {}