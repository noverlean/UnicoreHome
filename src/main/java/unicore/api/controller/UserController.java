package unicore.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import unicore.api.api.UserApi;
import unicore.api.entities.User;
import unicore.api.service.impl.UserServiceImpl;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserServiceImpl userService;

    @Override
    public ResponseEntity<User> getUser(Principal principal) {
        return ResponseEntity.ok(userService.getUser(principal.getName()));
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers(Principal principal) {
        return ResponseEntity.ok(userService.getAllUsers(principal.getName()));
    }

    @Override
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable("id") Long id) throws JsonProcessingException {
        return userService.downloadUser(id);
    }
}