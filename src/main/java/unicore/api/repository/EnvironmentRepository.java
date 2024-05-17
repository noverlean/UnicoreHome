package unicore.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unicore.api.entities.Environment;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnvironmentRepository extends CrudRepository<Environment, Long> {
    @Query("SELECT env FROM Environment env WHERE env.name = ?1")
    List<Environment> findByEmail(String environmentName);

    @Query(value = "SELECT env FROM Environment env, User u WHERE u.environment = env AND env.name = ?1 AND env.creator_email = ?3 AND u.email = ?2")
    Optional<Environment> findLinkedEnvironment(String environmentName, String email, String creatorEmail);
    @Query(value = "SELECT env FROM Environment env, User u WHERE u.environment = env AND env.name = ?1 AND env.creator_email = ?2 AND u.email = ?2")
    Optional<Environment> findLinkedEnvironment(String environmentName, String email);

    @Query(value = "SELECT env FROM Environment env WHERE env.name = ?1 AND env.creator_email = ?2")
    Optional<Environment> find(String environmentName, String creatorEmail);
}
