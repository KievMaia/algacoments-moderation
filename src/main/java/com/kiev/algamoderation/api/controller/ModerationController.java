package com.kiev.algamoderation.api.controller;

import com.kiev.algamoderation.api.dto.request.ModerationInput;
import com.kiev.algamoderation.api.dto.response.ModerationOutput;
import com.kiev.algamoderation.domain.service.CensorshipService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/moderate")
@Slf4j
public class ModerationController {

    private final CensorshipService censorshipService;

    public ModerationController(CensorshipService censorshipService) {
        this.censorshipService = censorshipService;
    }

    @PostMapping
    public ResponseEntity<ModerationOutput> moderate(@Valid @RequestBody ModerationInput input) {
        log.info("Recebida solicitação de moderação para o comentário ID: {}", input.commentId());
        var result = censorshipService.analyze(input.text());
        log.info("Resultado da moderação para {}: Aprovado? {}", input.commentId(), result.approved());
        return ResponseEntity.ok(result);
    }
}