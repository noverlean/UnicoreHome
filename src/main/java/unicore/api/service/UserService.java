package unicore.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import unicore.api.dto.RegistrationCredentials;
import unicore.api.entities.Environment;
import unicore.api.entities.User;

import java.util.List;

public interface UserService {
    @Transactional
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
    User createNewUser(RegistrationCredentials registrationCredentials);
    User getUser(String email);
    User linkTo(String email, Environment environment);
    List<User> getAllUsers(String email);
    User getUserById(Long id);
    ResponseEntity<InputStreamResource> downloadUser(Long id) throws JsonProcessingException;
}
