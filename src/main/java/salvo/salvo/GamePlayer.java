package salvo.salvo;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long gamePlayerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;


    @OneToMany(mappedBy="gamePlayer", fetch=FetchType.EAGER)//------------------------?
    Set<Ship>ships = new HashSet<>(); //-------------------?

    private Date date;

    public GamePlayer() { }

    public GamePlayer(Game game, Player player, Date date) {
        this.game = game;
        this.player = player;
        this.date = date;
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
        System.out.println("testing-------------------------------");
        return ships;

    }

    public void setShips(Set<Ship> ships) {
        this.ships = ships;
    }
    public void addShip(Ship ship) {
        ship.setGamePlayer(this);
        this.ships.add(ship);

    }
}