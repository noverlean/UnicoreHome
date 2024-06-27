package unicore.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unicore.api.dto.RegistrationCredentials;
import unicore.api.dto.UserEmailCodeDto;
import unicore.api.dto.tickets.TicketContent;
import unicore.api.entities.Environment;
import java.util.ArrayList;
import unicore.api.entities.Ticket;
import unicore.api.entities.User;
import unicore.api.repository.TicketRepository;
import unicore.api.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    ObjectMapper mapper = new ObjectMapper();

    public User createTicket(String email, String title, String content) throws JsonProcessingException {
        User user = userService.getUser(email);

        TicketContent ticketContent = new TicketContent(
                content,
                new Date(),
                user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"))
        );
        List<TicketContent> messages = new ArrayList<>();
        messages.add(ticketContent);
//        messages.sort((t1, t2) -> t1.getSendTime().compareTo(t2.getSendTime()) * -1);
        String json = mapper.writeValueAsString(messages);

        Ticket ticket = new Ticket(
                title,
                json,
                user
        );
        ticket.print();
        ticketRepository.save(ticket);

        user.getTickets().add(ticket);
        return userRepository.save(user);
    }

    public List<Ticket> getFreeTickets() {
        return ticketRepository.findAllFree();
    }

    @Transactional
    public ResponseEntity<User> acceptTicket(String email, Long id) {
        User admin = userService.getUser(email);
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if (ticket == null || ticket.getSupport() != null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ticket.setSupport(admin);
        ticketRepository.save(ticket);
        admin.getSupportTickets().add(ticket);
        return ResponseEntity.ok(userRepository.save(admin));
    }

    public ResponseEntity<User> deleteTicket(String email, Long id) {
        User user = userService.getUser(email);
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if (ticket == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user.getTickets().remove(ticket);


        if (ticket.getSupport() != null)
        {
            User support = userService.getUser(ticket.getSupport().getEmail());
            support.getSupportTickets().remove(ticket);
            userRepository.save(support);
        }

        user = userRepository.save(user);
        ticketRepository.delete(ticket);

        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> updateTicket(String email, Long id, String content) throws JsonProcessingException {
        User user = userService.getUser(email);
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if (ticket == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            boolean isUser = ticket.getUser().equals(user);
            boolean isSupport = ticket.getSupport().equals(user);
            if (!(isUser || isSupport))
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

        TicketContent ticketContent = new TicketContent(
                content,
                new Date(),
                user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"))
        );

        List<TicketContent> messages = mapper.readValue(ticket.getContent(), new TypeReference<>(){});
        messages.add(ticketContent);
        String json = mapper.writeValueAsString(messages);

        ticket.setContent(json);
        ticket.setChangeTimestamp(new Date());
        ticket = ticketRepository.save(ticket);

        List<Ticket> tickets = new ArrayList<>(user.getTickets().stream().toList());
        List<Ticket> supportTickets = new ArrayList<>(user.getSupportTickets().stream().toList());

        if (tickets.remove(ticket))
        {
            tickets.add(0, ticket);
            user.setTickets(tickets);
        }
        if (supportTickets.remove(ticket))
        {
            supportTickets.add(0, ticket);
            user.setSupportTickets(supportTickets);
        }

        return ResponseEntity.ok(userRepository.save(user));
    }

}
