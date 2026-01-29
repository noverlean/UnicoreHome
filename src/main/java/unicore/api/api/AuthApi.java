package unicore.api.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import unicore.api.dto.ConfirmEmailDto;
import unicore.api.dto.JwtRequest;
import unicore.api.dto.JwtResponse;
import unicore.api.dto.UserEmailCodeDto;

@CrossOrigin(origins = "*")
public interface AuthApi {
    @PostMapping("/login")
    ResponseEntity<JwtResponse> logIn(@RequestBody JwtRequest jwtRequest);

    @PostMapping("/signup/code")
    ResponseEntity<JwtResponse> confirmEmail(@RequestBody ConfirmEmailDto confirmUserEmailDto);

    @PostMapping("/signup")
    ResponseEntity<UserEmailCodeDto> signUp(@RequestBody JwtRequest jwtRequest);
}
