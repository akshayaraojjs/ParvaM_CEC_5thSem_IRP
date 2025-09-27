package com.gaming_app.controller;

import com.gaming_app.dto.request.GameRequest;
import com.gaming_app.dto.response.GameResponse;
import com.gaming_app.dto.response.ApiResponse;
import com.gaming_app.service.GameService;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    public final GameService gameService;
    public static final Logger logger = LoggerFactory.getLogger(GameController.class);

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<GameResponse>> createGame(@Valid @RequestBody GameRequest request) {
        logger.info("POST /games");
        GameResponse response = gameService.createGame(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new ApiResponse<>(201, "Game created successfully", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<GameResponse>>> getAllGames() {
        logger.info("GET /games");
        List<GameResponse> list = gameService.getAllGames();
        return ResponseEntity.ok(new ApiResponse<>(200, "Fetched all games successfully", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GameResponse>> getGameById(@PathVariable Long id) {
        logger.info("GET /games/{}", id);
        GameResponse response = gameService.getGameById(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Fetched game successfully", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<GameResponse>> updateGame(@PathVariable Long id, @Valid @RequestBody GameRequest request) {
        logger.info("PUT /games/{}", id);
        GameResponse response = gameService.updateGame(id, request);
        return ResponseEntity.ok(new ApiResponse<>(200, "Game updated successfully", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteGame(@PathVariable Long id) {
        logger.info("DELETE /games/{}", id);
        gameService.deleteGame(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Game deleted successfully", null));
    }
}