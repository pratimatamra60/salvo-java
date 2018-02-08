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

//@RestController
//@RequestMapping("/api")
 public class SalvoController {
    @Autowired //instance variable, to hold a GameRepository.
    private GameRepository repo;

    public SalvoController(GameRepository repo) {
        this.repo = repo;
    }


    @RequestMapping("/games") //it will be called to handle the URL /games.
    //public method that returns a List<Object>.
    public List<Long> getGames() {


/*
        ArrayList<Long> listOfIds = new ArrayList<Long>();

        List<Game> all = repo.findAll();
        all.forEach(new Consumer<Game>() {
            @Override
            public void accept(Game game) {
                long id = game.getId();
                listOfIds.add(id);
            }
        });
         return listOfIds;
*/



    //efficient way to do the same lines of code above
        return repo.findAll().stream().map(game -> game.getId()).collect(toList());

/*        Map<String, Integer> ages = new HashMap<>();
        ages.put("John", 32);
        ages.put("Mary", 26);
        ages.put("Bill", 19);*/


    }
}
