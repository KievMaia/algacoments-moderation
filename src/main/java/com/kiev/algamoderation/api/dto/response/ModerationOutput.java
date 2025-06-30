package com.kiev.algamoderation.api.dto.response;

public record ModerationOutput(
    boolean approved,
    String reason
) {}