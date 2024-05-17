package unicore.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unicore.api.entities.Lightning;
import unicore.api.entities.Switch;

@Repository
public interface SwitchRepository extends CrudRepository<Switch, Long> {
//    @Query("SELECT env FROM Environment env WHERE env.name = ?1")
//    List<Environment> findByEmail(String environmentName);
//
//    @Query(value = "SELECT env FROM Environment env, User u WHERE u.environment = env AND env.name = ?1 AND u.email = ?2")
//    Optional<Environment> findByNameAndUserEmail(String environmentName, String email)
}
