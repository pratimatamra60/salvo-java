package salvo.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static java.util.stream.Collectors.toList;

/*
REST controllers define methods that get data from a request,
optionally modify data in a database, and finally return an object
that can be converted to JSON to send back to the web client.
*/

@RestController
@RequestMapping("/api")
 public class SalvoController {
    @Autowired //instance variable, to hold a GameRepository.
    private GameRepository repo;

    public SalvoController(GameRepository repo) {
        this.repo = repo;
    }


    @RequestMapping("/games") //it will be called to handle the URL /games.
    //public method that returns a List<Object>.
    public List<Map> getGames() {


/*
        ArrayList<Long> listOfIds = new ArrayList<Long>();

        List<Game> all = repo.findAll();
        all.forEach(new Consumer<Game>() {
            @Override
            public void accept(Game game) {
                long id = game.getGameId();
                listOfIds.add(id);
            }
        });
         return listOfIds;
*/



    //efficient way to do the same lines of code above
    //task 4 //return repo.findAll().stream().map(game -> game.getId()).collect(toList());

        return repo.findAll().stream().map(game -> getGameMaps(game)).collect(toList());
    }
    //task5 it needs to return Maps

    private Map  getGameMaps(Game game){
        Map<String,Object> gameMap = new HashMap<>();
        gameMap.put("id",game.getGameId());
        gameMap.put("creationDate", game.getCreationDate());

        //Create a list with a Map for each game.
        //In the Map for each game, put keys and values
        //for the game ID, creation date, and gamePlayers.
        //For the value for the gamePlayers key, create a
        // List with a Map for each GamePlayer.

        List<Map> gamePlayerMap = game.getGamePlayers().stream().map(gamePlayer -> getGamePlayerMaps(gamePlayer)).collect(toList());
        gameMap.put("gamePlayers",gamePlayerMap);

        return gameMap;
    }
        // In the Map for each GamePlayer, put keys and values for the
        // GamePlayer ID and the player.

    private Map getGamePlayerMaps(GamePlayer gamePlayer){
        Map<String,Object> gamePlayerMap = new HashMap<>();
        gamePlayerMap.put("id",gamePlayer.getGamePlayerId());
        //gamePlayerMap.put("username", gamePlayer.getPlayer().getUserName());

        Map<String,Object> playerMap = getPlayerMaps(gamePlayer.getPlayer());
        gamePlayerMap.put("player", playerMap);

        return gamePlayerMap;


    }
        //  For the value of the player, create a Map with keys for
        //  the player ID and the player's email.
    private Map getPlayerMaps(Player player){
        Map<String,Object> playerMap = new HashMap<>();
        playerMap.put("id",player.getPlayerId());
        playerMap.put("username", player.getUserName());
        return playerMap;
    }
}
