package salvo.salvo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ship {
//-----------------------------Variables Declarations---------------------------------------------
    @Id //The annotation @Id says that the id instance variable holds the database key for this class.
    @GeneratedValue(strategy= GenerationType.AUTO) //    The annotation @GeneratedValue tells JPA to get the Id from the DBMS.
    private long shipId; // a unique number assigned to each ship.

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePlayer_id")
    private GamePlayer gamePlayer;

    private String shipType;//Destroyer, Submarine, Boat

    @ElementCollection
    @Column(name="location")
    private List<String> locations = new ArrayList<>();//--------?



//--------------------------Constructors--------------------------------------------
    public Ship() { }

    public Ship(GamePlayer gamePlayer, String shipType, List locations) {
        this.shipType = shipType;
        this.locations = locations;
        this.gamePlayer = gamePlayer;
    }
//------------------------Getters/Setters--------------------------------------------
    public long getShipId() {
        return shipId;
    }

     public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }
}
