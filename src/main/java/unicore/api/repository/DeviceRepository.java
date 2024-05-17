package unicore.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unicore.api.entities.Device;
import unicore.api.entities.Environment;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {
//    @Query("SELECT env FROM Environment env WHERE env.name = ?1")
//    List<Environment> findByEmail(String environmentName);
//
    @Query("SELECT d FROM Device d JOIN d.environment e JOIN e.users u WHERE d.name = :deviceName AND u.email = :email")
    Optional<Device> findByName(@Param("email") String email, @Param("deviceName") String deviceName);
}
