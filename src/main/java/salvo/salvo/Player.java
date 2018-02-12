package salvo.salvo;

// to save instances of Person in a persistent database, i.e., one that doesn't disappear when our program stops running.
//
//Thanks to JPA and Spring Boot, this can be done by adding very little to the class definition.

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Entity

public class Player {

    @Id //The annotation @Id says that the id instance variable holds the database key for this class.
    @GeneratedValue(strategy=GenerationType.AUTO) //    The annotation @GeneratedValue tells JPA to get the Id from the DBMS.


    private long playerId; // a unique number assigned to each player.
    private String userName;

    // default constructor
    //You must define a default (no-argument) constructor for any entity class.
    // That's what JPA will call to create new instances.
    public Player() { }


    //it allows you to initialize variable userName right after you create an object of Player
    public Player(String userName) {
        this.userName = userName;
    }

    public long getPlayerId() {
        return playerId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers;

    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setPlayer(this);
        gamePlayers.add(gamePlayer);
    }

//    @JsonIgnore
    public List<Game> getGames() {

        return gamePlayers.stream().map(sub -> sub.getGame()).collect(toList());
    }
}
