package com.gaming.service;

import com.gaming.dto.request.GameRequest;
import com.gaming.dto.response.GameResponse;
import com.gaming.exception.ResourceNotFoundException;
import com.gaming.model.Game;
import com.gaming.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private static final Logger logger = LoggerFactory.getLogger(GameService.class);

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    // Create
    public GameResponse createGame(GameRequest request) {
        logger.debug("Creating game: {}", request.getName());

        Game game = new Game();
        game.setName(request.getName());
        game.setDescription(request.getDescription());
        game.setCategory(request.getCategory());
        game.setCostPerMinute(request.getCostPerMinute());

        Game saved = gameRepository.save(game);
        return mapToResponse(saved);
    }

    // Read all
    public List<GameResponse> getAllGames() {
        logger.debug("Fetching all games");
        return gameRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Read one
    public GameResponse getGameById(Long id) {
        logger.debug("Fetching game with id={}", id);
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found with id: " + id));
        return mapToResponse(game);
    }

    // Update
    public GameResponse updateGame(Long id, GameRequest request) {
        logger.debug("Updating game id={}", id);
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found with id: " + id));

        game.setName(request.getName());
        game.setDescription(request.getDescription());
        game.setCategory(request.getCategory());
        game.setCostPerMinute(request.getCostPerMinute());

        Game updated = gameRepository.save(game);
        return mapToResponse(updated);
    }

    // Delete
    public void deleteGame(Long id) {
        logger.debug("Deleting game id={}", id);
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found with id: " + id));
        gameRepository.delete(game);
    }

    // Mapping
    private GameResponse mapToResponse(Game game) {
        GameResponse r = new GameResponse();
        r.setId(game.getId());
        r.setName(game.getName());
        r.setDescription(game.getDescription());
        r.setCategory(game.getCategory());
        r.setCostPerMinute(game.getCostPerMinute());
        return r;
    }
}