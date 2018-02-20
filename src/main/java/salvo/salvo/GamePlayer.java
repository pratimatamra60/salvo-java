package salvo.salvo;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class GamePlayer {


    //-----------------------------Variables Declarations---------------------------------------------

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long gamePlayerId;

    //FetchType.EAGER tells JPA to make sure that when a pet is loaded from the database
    // the owner data should be loaded too
    @ManyToOne(fetch = FetchType.EAGER)
    //the @JoinColumn annotation says which column has the ID of the Player
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;


    @OneToMany(mappedBy="gamePlayer", fetch=FetchType.EAGER)//------------------------?
    Set<Ship>ships = new HashSet<>(); //-------------------?


    @OneToMany(mappedBy="gamePlayer", fetch=FetchType.EAGER)//------------------------
    Set<Salvo>salvos = new HashSet<>(); //-------------------?

    private Date date;

    private Date joinDate;

//--------------------------Constructors--------------------------------------------

    public GamePlayer() { }

    public GamePlayer(Game game, Player player, Date joinDate ) {
        this.game = game;
        this.player = player;
       // this.date = date;
        this.joinDate = joinDate;

    }

    //------------------------Getters/Setters--------------------------------------------

    public long getGamePlayerId() {
        return gamePlayerId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Set<Ship> getShips() {
        return ships;
    }

    public void setShips(Set<Ship> ships) {
        this.ships = ships;
    }

    public Set<Salvo> getSalvos() {
        return salvos;
    }

    public void setSalvos(Set<Salvo> salvos) {
        this.salvos = salvos;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }


    //------------------------------methods-------------------------------------------
    public void addShip(Ship ship) {
        ship.setGamePlayer(this);
        this.ships.add(ship);
    }

    public void addSalvos(Salvo salvo) {
        salvo.setGamePlayer(this);
        this.salvos.add(salvo);
    }
}