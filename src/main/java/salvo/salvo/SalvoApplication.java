package salvo.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository) {
		return (args) -> {
		    //option 1
			//Player tom = new Player("tom@gmail.com"); //direct initialization

            //option 2
			Player tom = new Player ("tom@gmail.com");
            Player tim = new Player ("tim@hotmail.gov");
            Player jack = new Player ("jack@ymail.com");
            Player rose = new Player ("rose@blablamail.gov");
            Player jasmin = new Player ("jasmin@mail.com");

            playerRepository.save(tom);
            playerRepository.save(tim);
            playerRepository.save(jack);
            playerRepository.save(rose);
            playerRepository.save(jasmin);

            //option 3 shortcut
            /*Player jack = new Player("jack@hotmail.com");
            playerRepository.save(jack); //
            Player tim = new Player("tim@hotmail.com");
            playerRepository.save(tim);

            playerRepository.save(new Player("jeddy@hotmail.com")); //
            playerRepository.save(new Player("tom@hotmail.com"));*/

            Date date = new Date();

            Game game = new Game ();
            game.setCreationDate(date.getTime()+1000);
            gameRepository.save(game);
            Game game1 = new Game(date.getTime()+2000);
            gameRepository.save(game1);
            Game game2 = new Game(date.getTime()+3000);
            gameRepository.save(game2);
            Game game3 = new Game(date.getTime()+4000);
            gameRepository.save(game3);
            Game game4 = new Game(date.getTime()+5000);
            gameRepository.save(game4);
            Game game5 = new Game(date.getTime()+6000);
            gameRepository.save(game5);
            Game game6 = new Game(date.getTime()+7000);
            gameRepository.save(game6);


            /*gameRepository.save(new Game(date.toString()));
            gameRepository.save(new Game(date.getTime()));*/

            /*long  time ;
            time = date.getTime();*/

            GamePlayer gamePlayer = new GamePlayer(game, tom, date);
            gamePlayerRepository.save(gamePlayer);

            GamePlayer gamePlayer1 = new GamePlayer(game, tim, date);
            gamePlayerRepository.save(gamePlayer1);

            GamePlayer gamePlayer2 = new GamePlayer(game1, jack, date);
            gamePlayerRepository.save(gamePlayer2);

            GamePlayer gamePlayer3 = new GamePlayer(game1, tim, date);
            gamePlayerRepository.save(gamePlayer3);

            GamePlayer gamePlayer4 = new GamePlayer(game2, jack, date);
            gamePlayerRepository.save(gamePlayer4);

            GamePlayer gamePlayer5 = new GamePlayer(game2, rose, date);
            gamePlayerRepository.save(gamePlayer5);

            GamePlayer gamePlayer6 = new GamePlayer(game3, jasmin, date);
            gamePlayerRepository.save(gamePlayer6);

            GamePlayer gamePlayer7 = new GamePlayer(game3, tim, date);
            gamePlayerRepository.save(gamePlayer7);

            GamePlayer gamePlayer8 = new GamePlayer(game4, tim, date);
            gamePlayerRepository.save(gamePlayer8);

            GamePlayer gamePlayer9 = new GamePlayer(game4, rose, date);
            gamePlayerRepository.save(gamePlayer9);

            GamePlayer gamePlayer10 = new GamePlayer(game5, rose, date);
            gamePlayerRepository.save(gamePlayer10);

            GamePlayer gamePlayer11 = new GamePlayer(game5, tom, date);
            gamePlayerRepository.save(gamePlayer11);

            GamePlayer gamePlayer12 = new GamePlayer(game6, jack, date);
            gamePlayerRepository.save(gamePlayer12);


            List<String> locations = Arrays.asList("H1", "H3", "D5", "B2");



          Ship ship  = new Ship (gamePlayer, "Destroyer", locations);
          shipRepository.save(ship);

           Ship ship1 = new Ship (gamePlayer1,"Submarine",Arrays.asList("A2", "A1", "D5"));
            shipRepository.save(ship1);

            Ship ship2 = new Ship (gamePlayer2,"Patrol Boat", Arrays.asList("A2", "A1", "D5"));
            shipRepository.save(ship2);

            Ship ship3 = new Ship (gamePlayer6,"Cruiser",Arrays.asList("H6", "A1", "D5"));
            shipRepository.save(ship3);

            Ship ship4 = new Ship (gamePlayer3,"Destroyer",Arrays.asList("G2", "A1", "D5"));
            shipRepository.save(ship4);

            Ship ship5 = new Ship (gamePlayer4,"Submarine",Arrays.asList("D2", "B1", "D5"));
            shipRepository.save(ship5);

            Ship ship6 = new Ship (gamePlayer8,"Submarine",Arrays.asList("F2", "C1", "D5"));
            shipRepository.save(ship6);


            gamePlayer1.addShip(ship);
            gamePlayer1.addShip(ship1);
            gamePlayer1.addShip(ship2);
            gamePlayer1.addShip(ship3);
            gamePlayer1.addShip(ship4);
            gamePlayer1.addShip(ship5);
            gamePlayer1.addShip(ship6);
        };
	}
}
