package salvo.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;


@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository repository, GameRepository repo, GamePlayerRepository rep) {
		return (args) -> {
		    //option 1
			//Player tom = new Player("tom@gmail.com"); //direct initialization

            //option 2
			Player tom = new Player ("tom@gmail.com");
            Player tim = new Player ("tim@hotmail.gov");
            Player jack = new Player ("jack@ymail.com");
            Player rose = new Player ("rose@blablamail.gov");
            Player jasmin = new Player ("jasmin@mail.com");

            repository.save(tom);
            repository.save(tim);
            repository.save(jack);
            repository.save(rose);
            repository.save(jasmin);


            //option 3 shortcut
            /*Player jack = new Player("jack@hotmail.com");
            repository.save(jack); //
            Player tim = new Player("tim@hotmail.com");
            repository.save(tim);

            repository.save(new Player("jeddy@hotmail.com")); //
            repository.save(new Player("tom@hotmail.com"));*/

            Date date = new Date();

            Game game = new Game ();
            game.setCreationDate(date.getTime()+1000);
            repo.save(game);
            Game game1 = new Game(date.getTime()+2000);
            repo.save(game1);
            Game game2 = new Game(date.getTime()+3000);
            repo.save(game2);
            Game game3 = new Game(date.getTime()+4000);
            repo.save(game3);
            Game game4 = new Game(date.getTime()+5000);
            repo.save(game4);
            Game game5 = new Game(date.getTime()+6000);
            repo.save(game5);
            Game game6 = new Game(date.getTime()+7000);
            repo.save(game6);


            /*repo.save(new Game(date.toString()));
            repo.save(new Game(date.getTime()));*/

            /*long  time ;
            time = date.getTime();*/

            GamePlayer gamePlayer = new GamePlayer(game, tom, date);
            rep.save(gamePlayer);

            GamePlayer gamePlayer1 = new GamePlayer(game, tim, date);
            rep.save(gamePlayer1);

            GamePlayer gamePlayer2 = new GamePlayer(game1, jack, date);
            rep.save(gamePlayer2);

            GamePlayer gamePlayer3 = new GamePlayer(game1, tim, date);
            rep.save(gamePlayer3);

            GamePlayer gamePlayer4 = new GamePlayer(game2, jack, date);
            rep.save(gamePlayer4);

            GamePlayer gamePlayer5 = new GamePlayer(game2, rose, date);
            rep.save(gamePlayer5);

            GamePlayer gamePlayer6 = new GamePlayer(game3, jasmin, date);
            rep.save(gamePlayer6);

            GamePlayer gamePlayer7 = new GamePlayer(game3, tim, date);
            rep.save(gamePlayer7);

            GamePlayer gamePlayer8 = new GamePlayer(game4, tim, date);
            rep.save(gamePlayer8);

            GamePlayer gamePlayer9 = new GamePlayer(game4, rose, date);
            rep.save(gamePlayer9);

            GamePlayer gamePlayer10 = new GamePlayer(game5, rose, date);
            rep.save(gamePlayer10);

            GamePlayer gamePlayer11 = new GamePlayer(game5, tom, date);
            rep.save(gamePlayer11);

            GamePlayer gamePlayer12 = new GamePlayer(game6, jack, date);
            rep.save(gamePlayer12);


        };
	}
    /*public CommandLineRunner initData(ShipRepository repository) {
        return (args) -> {
            Ship ship = new Ship ();
        };
	}
    public CommandLineRunner initData(SalvoRepository repository) {
        return (args) -> {
            Salvo salvo = new Salvo ();
        };
    }*/
}
