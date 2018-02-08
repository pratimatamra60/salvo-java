package salvo.salvo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
//JpaRepository is a interface
public interface GameRepository extends JpaRepository<Game, Long> {

}


