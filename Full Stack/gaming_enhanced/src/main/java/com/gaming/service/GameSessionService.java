package com.gaming.service;

import com.gaming.dto.request.GameSessionRequest;
import com.gaming.dto.response.GameSessionResponse;
import com.gaming.dto.response.ApiResponse;
import com.gaming.model.Game;
import com.gaming.model.GameSession;
import com.gaming.model.Member;
import com.gaming.repository.GameRepository;
import com.gaming.repository.GameSessionRepository;
import com.gaming.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameSessionService {

    private final GameSessionRepository sessionRepository;
    private final MemberRepository memberRepository;
    private final GameRepository gameRepository;

    // scaling factor: 1 real minute = 5 virtual minutes
    private static final int TIME_SCALE = 5;

    public GameSessionService(GameSessionRepository sessionRepository,
                              MemberRepository memberRepository,
                              GameRepository gameRepository) {
        this.sessionRepository = sessionRepository;
        this.memberRepository = memberRepository;
        this.gameRepository = gameRepository;
    }

    public ApiResponse<GameSessionResponse> startSession(GameSessionRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Game game = gameRepository.findById(request.getGameId())
                .orElseThrow(() -> new RuntimeException("Game not found"));

        GameSession session = new GameSession();
        session.setMember(member);
        session.setGame(game);
        session.setStartedAt(LocalDateTime.now());

        sessionRepository.save(session);

        GameSessionResponse response = new GameSessionResponse();
        response.setGameName(game.getName());
        response.setMemberName(member.getName());
        response.setStartedAt(session.getStartedAt());

        return new ApiResponse<>(HttpStatus.OK.value(),
                "Game session started successfully for member " + member.getName(),
                response);
    }

    public ApiResponse<GameSessionResponse> endSession(Long sessionId) {
        GameSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        if (session.getEndedAt() != null) {
            throw new RuntimeException("Session already ended");
        }

        session.setEndedAt(LocalDateTime.now());

        // Calculate scaled duration
        long realMinutes = Duration.between(session.getStartedAt(), session.getEndedAt()).toMinutes();
        long virtualMinutes = realMinutes * TIME_SCALE;

        double cost = virtualMinutes * session.getGame().getCostPerMinute();

        Member member = session.getMember();
        if (member.getBalance() < cost) {
            throw new RuntimeException("Insufficient balance");
        }

        member.setBalance(member.getBalance() - cost);
        memberRepository.save(member);

        session.setMinutesPlayed((int) virtualMinutes);
        session.setCost(cost);
        sessionRepository.save(session);

        GameSessionResponse response = new GameSessionResponse();
        response.setGameName(session.getGame().getName());
        response.setMemberName(member.getName());
        response.setStartedAt(session.getStartedAt());
        response.setEndedAt(session.getEndedAt());
        response.setCost(cost);
        response.setUpdatedBalance(member.getBalance());

        return new ApiResponse<>(HttpStatus.OK.value(),
                "Game session ended successfully for member " + member.getName(),
                response);
    }

    public ApiResponse<List<GameSessionResponse>> listAllSessions() {
        List<GameSessionResponse> sessions = sessionRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return new ApiResponse<>(HttpStatus.OK.value(), "All game sessions fetched successfully", sessions);
    }

    public ApiResponse<List<GameSessionResponse>> listSessionsByMember(Long memberId) {
        List<GameSessionResponse> sessions = sessionRepository.findByMemberId(memberId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return new ApiResponse<>(HttpStatus.OK.value(), "Sessions for member fetched successfully", sessions);
    }

    private GameSessionResponse mapToResponse(GameSession session) {
        GameSessionResponse response = new GameSessionResponse();
        response.setId(session.getId());
        response.setGameName(session.getGame().getName());
        response.setMemberName(session.getMember().getName());
        response.setStartedAt(session.getStartedAt());
        response.setEndedAt(session.getEndedAt());
        response.setCost(session.getCost());
        response.setUpdatedBalance(session.getMember().getBalance());
        return response;
    }
}