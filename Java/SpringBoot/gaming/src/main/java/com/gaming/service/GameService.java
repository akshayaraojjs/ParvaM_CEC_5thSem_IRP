package com.gaming.service;

import com.gaming.model.Game;
import com.gaming.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    public Game updateGame(Long id, Game gameDetails) {
        Game game = gameRepository.findById(id).orElse(null);
        if (game != null) {
            game.setName(gameDetails.getName());
            game.setDescription(gameDetails.getDescription());
            game.setCategory(gameDetails.getCategory());
            game.setCostPerMinute(gameDetails.getCostPerMinute());
            return gameRepository.save(game);
        }
        return null;
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}