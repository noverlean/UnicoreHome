package unicore.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import unicore.api.entities.Ticket;
import unicore.api.entities.User;

import java.util.List;

public interface TicketService {
    public User createTicket(String email, String title, String content) throws JsonProcessingException;
    public List<Ticket> getFreeTickets();

    @Transactional
    public ResponseEntity<User> acceptTicket(String email, Long id);
    public ResponseEntity<User> deleteTicket(String email, Long id);
    public ResponseEntity<User> updateTicket(String email, Long id, String content) throws JsonProcessingException;
}
