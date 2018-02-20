package salvo.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    private GameRepository gameRepository;

    @Autowired //instance variable, to hold a GamePlayerRepository.
    private GamePlayerRepository gamePlayerRepository;

    @Autowired //instance variable, to hold a GamePlayerRepository.
    private PlayerRepository PlayerRepository;

    @Autowired //instance variable, to hold a GameRepository.
    private SalvoRepository salvoRepository;


    public SalvoController(GameRepository repo) {
        this.gameRepository = gameRepository;
    }


    @RequestMapping("/games") //it will be called to handle the URL /games.
    //public method that returns a List<Object>.
    public List<Map> getAll() {

       /* ArrayList<Long> listOfIds = new ArrayList<Long>();

        List<Game> all = repo.findAll();
        all.forEach(new Consumer<Game>() {
            @Override
            public void accept(Game game) {
                long id = game.getGameId();
                listOfIds.add(id);
            }
        });
         return listOfIds; */

    //efficient way to do the same lines of code above
    //task 4 //return repo.findAll().stream().map(game -> game.getId()).collect(toList());

        return gameRepository.findAll().stream().map(game -> getGameMaps(game)).collect(toList());// to create the map that displays all objects
    }
    //task5 it needs to return Maps

    //Create a list with a Map for each game.
    //In the Map for each game, put keys and values //for the game ID, creation date,
    private Map  getGameMaps(Game game){
        Map<String,Object> gameMap = new HashMap<>();
        gameMap.put("id",game.getGameId());
        gameMap.put("creationDate", game.getCreationDate());


        //gamePlayers.
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
        //option 1
        gamePlayerMap.put("player", getPlayerMaps(gamePlayer.getPlayer()));

        //option2
        /*Map<String,Object> playerMap = getPlayerMaps(gamePlayer.getPlayer());
        gamePlayerMap.put("player", playerMap);*/

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

    @RequestMapping("/game_view/{gamePlayerId}") //it will be called to handle the URL /game_view/nn.
    public  Map<String,Object>   findGamePlayerId(@PathVariable Long gamePlayerId){
        GamePlayer gamePlayer = gamePlayerRepository.findByGamePlayerId(gamePlayerId); // this game player has all the info of a single
                                                                                       // gameplayer with given gamePlayerId
       return getGameView(gamePlayer);// Now returning getGameView as it takes gamePlayer and returns Map.
                                      // passing gamePlayer here has a specific id goes to getGameView function where
                                      // ship info is added for nice JSON
    }

    private Map getGameView(GamePlayer activeGamePlayer){
        Map<String,Object> gameViewMap = new HashMap<>();
        gameViewMap.put("id", activeGamePlayer.getGame().getGameId());
        gameViewMap.put("creationDate", activeGamePlayer.getGame().getCreationDate());

        List<Map> gamePlayerMap = activeGamePlayer.getGame().getGamePlayers().stream().map(gamePlayer -> getGamePlayerMaps(gamePlayer)).collect(toList());
        gameViewMap.put("gamePlayers",gamePlayerMap);

        List<Map> shipMap = activeGamePlayer.getShips().stream().map(ship -> getShipMaps(ship)).collect(toList());
        gameViewMap.put("ships",shipMap);

       /* List<Map> salvoMap = activeGamePlayer.getSalvos().stream().map(salvo -> getSalvoMaps(salvo)).collect(toList());
        gameViewMap.put("salvos",salvoMap);*/
        return gameViewMap;
    }

    private Map getShipMaps(Ship ship){
        Map<String,Object> shipMap = new HashMap<>();
        shipMap.put("type",ship.getShipType());
        shipMap.put("locations", ship.getLocations());
        return shipMap;
    }

   /* private Map getSalvoMaps(Salvo salvo){
        Map<String,Object> salvoMap = new HashMap<>();
        salvoMap.put("turn",salvo.getTurn());
        //List<Map> gamePlayerMap = salvo.getGamePlayer().getPlayer().stream().map(gamePlayer -> getGamePlayerMaps(gamePlayer)).collect(toList());
        //salvoMap.put();
        salvoMap.put("salvoLocations", salvo.getLocations());
        return salvoMap;
    }*/
}
