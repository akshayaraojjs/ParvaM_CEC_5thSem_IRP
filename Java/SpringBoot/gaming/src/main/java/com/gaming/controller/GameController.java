package com.gaming.controller;

import com.gaming.model.Game;
import com.gaming.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// API Endpoint
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // Calling the service to insert a new data along with Post Request 
    // Post Method is used to insert the data
    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return gameService.createGame(game);
    }

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable Long id) {
        return gameService.getGameById(id);
    }

    @PutMapping("/{id}")
    public Game updateGame(@PathVariable Long id, @RequestBody Game gameDetails) {
        return gameService.updateGame(id, gameDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
    }
}

// Ctrl + Shift + ~
// mvn spring-boot:run 