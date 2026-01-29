package unicore.api.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicore.api.dto.tickets.CreateTicketDto;
import unicore.api.dto.tickets.TicketMessageDto;
import unicore.api.entities.Ticket;
import unicore.api.entities.User;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*")
public interface TickerApi {
    @PostMapping("/ticket/create")
    ResponseEntity<User> createTicket(
            @RequestBody CreateTicketDto createTicketDto,
            Principal principal
    ) throws JsonProcessingException;

    @GetMapping("/tickets")
    ResponseEntity<List<Ticket>> getFreeTickets();

    @PostMapping("/ticket/accept/{id}")
    ResponseEntity<User> acceptTicket(@PathVariable("id") Long id, Principal principal);

    @DeleteMapping("/ticket/delete/{id}")
    ResponseEntity<User> deleteTicket(@PathVariable("id") Long id, Principal principal);

    @PutMapping("/ticket/send/{id}")
    ResponseEntity<User> updateTicket(
            @PathVariable("id") Long id,
            @RequestBody TicketMessageDto ticketMessageDto,
            Principal principal
    ) throws JsonProcessingException;
}
