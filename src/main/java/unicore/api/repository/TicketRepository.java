package unicore.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unicore.api.entities.Environment;
import unicore.api.entities.Ticket;
import unicore.api.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    @Override
    List<Ticket> findAll();

    @Query("SELECT tic FROM Ticket tic WHERE tic.support is null ORDER BY tic.changeTimestamp DESC")
    List<Ticket> findAllFree();
}
