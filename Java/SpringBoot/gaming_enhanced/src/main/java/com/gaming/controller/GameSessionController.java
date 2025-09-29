package com.gaming.controller;

import com.gaming.dto.request.GameSessionRequest;
import com.gaming.dto.response.ApiResponse;
import com.gaming.dto.response.GameSessionResponse;
import com.gaming.service.GameSessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
public class GameSessionController {

    private final GameSessionService gameSessionService;

    public GameSessionController(GameSessionService gameSessionService) {
        this.gameSessionService = gameSessionService;
    }

    @PostMapping("/start")
    public ApiResponse<GameSessionResponse> startSession(@RequestBody GameSessionRequest request) {
        return gameSessionService.startSession(request);
    }

    @PutMapping("/end/{sessionId}")
    public ApiResponse<GameSessionResponse> endSession(@PathVariable Long sessionId) {
        return gameSessionService.endSession(sessionId);
    }

    @GetMapping
    public ApiResponse<List<GameSessionResponse>> getAllSessions() {
        return gameSessionService.listAllSessions();
    }

    @GetMapping("/member/{memberId}")
    public ApiResponse<List<GameSessionResponse>> getMemberSessions(@PathVariable Long memberId) {
        return gameSessionService.listSessionsByMember(memberId);
    }
}