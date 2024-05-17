package unicore.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicore.api.entities.User;
import unicore.api.service.UserService;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<User> getUser(Principal principal) {
        return ResponseEntity.ok(userService.getUser(principal.getName()));
    }
}