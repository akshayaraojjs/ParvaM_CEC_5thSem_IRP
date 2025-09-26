// Path of the current file
package com.gaming.service;

// Referring to Game Model
import com.gaming.model.Game;
// Referring to Game Repository
import com.gaming.repository.GameRepository;
// To use Service, we need to import it
import org.springframework.stereotype.Service;

// To work with List or Array
import java.util.List;

// Annotation for Service
@Service
public class GameService {
    // Initialize the Repository to make use of ORM methods
    // GameRepository is a Class
    // gameRepository is an object or instance of the Class GameRepository
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    // Create Operation or Insertion
    // Game model is used as a reference for game object
    // save(game) - game object can be reffered as the API request Body/Payload: 
    // {
    //   "name" : "Subway Surfers",
    //   "description": "Adventure Game with Boy running on the Railway Track",
    //   "category": "Adventure",
    //   "costPerMinute": 4.86
    // }
    // ORM used : save()
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    // List is used to fetch all the data present in the games table
    // ORM used : findAll()
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    // ORM used : findById(id), id refers to primary key value 
    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    // update refers to edit(show old data) and save(update the new data or modified data)
    // ORM used : findById(id), save() id refers to primary key value and gameDetails refers to the new or modified data
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

    // ORM used : deleteById(id), id refers to primary key value 
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}