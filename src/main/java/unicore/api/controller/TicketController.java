package unicore.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicore.api.api.TickerApi;
import unicore.api.dto.tickets.CreateTicketDto;
import unicore.api.dto.tickets.TicketMessageDto;
import unicore.api.entities.Ticket;
import unicore.api.entities.User;
import unicore.api.service.impl.TicketServiceImpl;

import java.security.Principal;
import java.util.List;

@RestController

@RequiredArgsConstructor
public class TicketController implements TickerApi {
    private final TicketServiceImpl ticketService;

    @Override
    public ResponseEntity<User> createTicket(@RequestBody CreateTicketDto createTicketDto, Principal principal) throws JsonProcessingException {
        System.out.println(createTicketDto);
        return ResponseEntity.ok(ticketService.createTicket(principal.getName(), createTicketDto.getTitle(), createTicketDto.getContent()));
    }

    @Override
    public ResponseEntity<List<Ticket>> getFreeTickets() {
        return ResponseEntity.ok(ticketService.getFreeTickets());
    }

    @Override
    public ResponseEntity<User> acceptTicket(@PathVariable("id") Long id, Principal principal) {
        return ticketService.acceptTicket(principal.getName(), id);
    }

    @Override
    public ResponseEntity<User> deleteTicket(@PathVariable("id") Long id, Principal principal) {
        return ticketService.deleteTicket(principal.getName(), id);
    }

    @Override
    public ResponseEntity<User> updateTicket(@PathVariable("id") Long id, @RequestBody TicketMessageDto ticketMessageDto, Principal principal) throws JsonProcessingException {
        return ticketService.updateTicket(principal.getName(), id, ticketMessageDto.getContent());
    }
}