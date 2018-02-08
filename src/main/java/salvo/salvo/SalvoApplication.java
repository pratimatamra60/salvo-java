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
			Player tom = new Player ();
            tom.setUserName("tom@gmail.com");
            repository.save(tom);

			//option 3 shortcut
            repository.save(new Player("jack@hotmail.com")); //
            repository.save(new Player("tom@hotmail.com"));

            Date date = new Date();
            Game game = new Game ();
            Game game1 = new Game ();
            Game game2 = new Game();
            game2.setDate(date.toString());


            repo.save(game);
            repo.save(game1);
            repo.save (game2);


            GamePlayer gamePlayer = new GamePlayer(game, tom, date);
            rep.save(gamePlayer);

        };
	}

}
