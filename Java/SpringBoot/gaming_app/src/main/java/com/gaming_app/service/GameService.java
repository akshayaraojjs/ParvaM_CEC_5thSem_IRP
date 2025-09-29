package com.gaming_app.service;

// importing the request to get the data from the user in the proper format
import com.gaming_app.dto.request.GameRequest;
// importing the response to show the response when something happens
import com.gaming_app.dto.response.GameResponse;
// importing the table or Model to store or fetch the data
import com.gaming_app.model.Game;
// importing the jpa to use the ORM methods Ex: save(), findAll()
import com.gaming_app.repository.GameRepository;
// To show the error message, we use Exception
import com.gaming_app.exception.ResourceNotFoundException;

// To use logger, we need to import slf4j package
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

// To work with arrays, we use List
import java.util.List;
// To work with stream api's, we need Collectors: ex: map, filter & reduce
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;
    // Initializing the logger
    private static final Logger logger = LoggerFactory.getLogger(GameService.class);

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameResponse createGame(GameRequest request) {
        // Success:  Status : 201 - Created
        // Failure:  Status : 400 - Bad Request
        // Message not printing
        logger.debug("Creating game: {}", request.getName());

        // Game Model is used as an instance to create a game object
        Game game = new Game();
        // Sending the request data using Setter function
        game.setName(request.getName());
        game.setDescription(request.getDescription());
        game.setCategory(request.getCategory());
        game.setCostPerMinute(request.getCostPerMinute());

        // To show the response for the request, we are passing the success or failure message to the ApiResponse
        Game saved = gameRepository.save(game);
        return mapToResponse(saved);
    }

    public List<GameResponse> getAllGames() {
        logger.debug("Fetching all games");
        // Fetching all data in the form of Array or List and showing in Proper format as a Response using stream API - map
        // Success : Status - 200 - Ok
        return gameRepository.findAll().stream()
                                       .map(this::mapToResponse)
                                       .collect(Collectors.toList());
    }

    public GameResponse getGameById(Long id) {
        logger.debug("Fetching game with id = {}", id);
        // Success : Status - 200 - Ok
        // Failure:  Status : 404 - Not Found
        Game game = gameRepository.findById(id)
        // ResourceNotFoundException will throw or send a message that it is not found with status code - 404
                                  .orElseThrow(() -> new ResourceNotFoundException("Game not found with id: " + id));
        return mapToResponse(game);
    }

    public GameResponse updateGame(Long id, GameRequest request) {
        logger.debug("Updating game id = {}", id);
        Game game = gameRepository.findById(id)
                                  .orElseThrow(() -> new ResourceNotFoundException("Game not found with id: " + id));
        
        game.setName(request.getName());
        game.setDescription(request.getDescription());
        game.setCategory(request.getCategory());
        game.setCostPerMinute(request.getCostPerMinute());

        Game updated = gameRepository.save(game);
        return mapToResponse(updated);
    }

    public void deleteGame(Long id) {
        logger.debug("Deleting game id = {}", id);
        Game game = gameRepository.findById(id)
                                  .orElseThrow(() -> new ResourceNotFoundException("Game not found with id: " + id));
        gameRepository.delete(game);
    }

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