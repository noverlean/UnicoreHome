package unicore.api.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import unicore.api.dto.*;

public interface AuthService {
    ResponseEntity<JwtResponse> logIn(@RequestBody JwtRequest jwtRequest);
    ResponseEntity<UserEmailCodeDto> signUp(@RequestBody JwtRequest jwtRequest);
    ResponseEntity<JwtResponse> createNewUser(@RequestBody RegistrationCredentials registrationCredentials);
    ResponseEntity<JwtResponse> confirmEmail(@RequestBody ConfirmEmailDto confirmEmailDto);
    RegistrationCredentials getRegistrationCredentials(String email);
}
