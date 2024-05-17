package unicore.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicore.api.dto.tickets.AcceptDeleteTicketDto;
import unicore.api.dto.tickets.CreateTicketDto;
import unicore.api.dto.tickets.TicketMessageDto;
import unicore.api.entities.Ticket;
import unicore.api.entities.User;
import unicore.api.service.TicketService;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/ticket/create")
    public ResponseEntity<User> createTicket(@RequestBody CreateTicketDto createTicketDto, Principal principal) throws JsonProcessingException {
        System.out.println(createTicketDto);
        return ResponseEntity.ok(ticketService.createTicket(principal.getName(), createTicketDto.getTitle(), createTicketDto.getContent()));
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getFreeTickets() {
        return ResponseEntity.ok(ticketService.getFreeTickets());
    }

    @PostMapping("/ticket/accept/{id}")
    public ResponseEntity<User> acceptTicket(@PathVariable("id") Long id, Principal principal) {
        return ticketService.acceptTicket(principal.getName(), id);
    }

    @DeleteMapping("/ticket/delete/{id}")
    public ResponseEntity<User> deleteTicket(@PathVariable("id") Long id, Principal principal) {
        return ticketService.deleteTicket(principal.getName(), id);
    }

    @PutMapping("/ticket/send/{id}")
    public ResponseEntity<User> updateTicket(@PathVariable("id") Long id, @RequestBody TicketMessageDto ticketMessageDto, Principal principal) throws JsonProcessingException {
        return ticketService.updateTicket(principal.getName(), id, ticketMessageDto.getContent());
    }
}