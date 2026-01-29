package unicore.api.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import unicore.api.entities.User;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*")
public interface UserApi {
    @GetMapping("/user")
    ResponseEntity<User> getUser(Principal principal);

    @GetMapping("/users")
    ResponseEntity<List<User>> getAllUsers(Principal principal);

    @GetMapping("/download/{id}")
    ResponseEntity<InputStreamResource> downloadFile(@PathVariable("id") Long id) throws JsonProcessingException;
}
