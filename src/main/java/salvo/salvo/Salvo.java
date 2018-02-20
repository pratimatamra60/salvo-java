package salvo.salvo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Salvo {
    //-----------------------------Variables Declarations---------------------------------------------
    @Id //The annotation @Id says that the id instance variable holds the database key for this class.
    @GeneratedValue(strategy= GenerationType.AUTO) //    The annotation @GeneratedValue tells JPA to get the Id from the DBMS.
    private long salvoId; // a unique number assigned to each salvo.

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePlayer_id")
    private GamePlayer gamePlayer;

    private int turn;// hit turns

    @ElementCollection
    @Column(name="salvoLocation")
    private List<String> salvoLocations = new ArrayList<>();//list of salvo locations

    //--------------------------Constructors--------------------------------------------

    public Salvo(){}
    public Salvo(GamePlayer gamePlayer, int turn, List locations) {
        this.gamePlayer = gamePlayer;
        this.turn = turn;
        this.salvoLocations = locations;
    }


    //------------------------Getters/Setters--------------------------------------------
    public long getSalvoId() {
        return salvoId;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
    public List<String> getLocations() {
        return salvoLocations;
    }

    public void setLocations(List<String> locations) {
        this.salvoLocations = locations;
    }
}
