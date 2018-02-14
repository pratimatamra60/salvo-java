package salvo.salvo;

// to save instances of Person in a persistent database, i.e., one that doesn't disappear when our program stops running.
//
//Thanks to JPA and Spring Boot, this can be done by adding very little to the class definition.

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Entity

public class Game {

    @Id //The annotation @Id says that the id instance variable holds the database key for this class.
    @GeneratedValue(strategy=GenerationType.AUTO) //    The annotation @GeneratedValue tells JPA to get the Id from the DBMS.
    private long gameId; // a unique number assigned to each player.
    private long creationDate;


    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers;


    public Game() { }


    //it allows you to initialize variable userName right after you create an object of Player

    public Game(long creationDate) {
        this.creationDate = creationDate;
    }

   public long getGameId() {
       return gameId;
   }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setGame(this);
        gamePlayers.add(gamePlayer);
    }

    @JsonIgnore
    public List<Player> getPlayers() {
        return gamePlayers.stream().map(sub -> sub.getPlayer()).collect(toList());
    }
}
