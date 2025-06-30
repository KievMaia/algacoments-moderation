package com.kiev.algamoderation.domain.service;

import com.kiev.algamoderation.api.dto.response.ModerationOutput;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.regex.Pattern;

@Service
public class CensorshipService {

    private static final Set<String> FORBIDDEN_WORDS = Set.of(
            "ódio",
            "xingamento",
            "spam",
            "lixo"
    );

    private static final Pattern FORBIDDEN_WORDS_PATTERN;

    static {
        var regex = "\\b(" + String.join("|", FORBIDDEN_WORDS) + ")\\b";
        FORBIDDEN_WORDS_PATTERN = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);
    }

    public ModerationOutput analyze(String text) {
        boolean containsForbiddenWord = FORBIDDEN_WORDS_PATTERN.matcher(text).find();

        if (containsForbiddenWord) {
            return new ModerationOutput(false, "O comentário contém palavras impróprias.");
        }

        return new ModerationOutput(true, "Comentário aprovado.");
    }
}