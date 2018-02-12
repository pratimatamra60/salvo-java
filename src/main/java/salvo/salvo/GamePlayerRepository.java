package salvo.salvo;

//The GamePlayerRepository class is responsible for persisting instances of GamePlayer to database H2
// in our case, and retrieving instances.Notice how the Spring Data framework encourages
// separate repositories for separate classes.
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
//JpaRepository is a interface
public interface GamePlayerRepository extends JpaRepository<GamePlayer, Long> {

}

