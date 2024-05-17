package unicore.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicore.api.dto.ConfirmEmailDto;
import unicore.api.dto.JwtRequest;
import unicore.api.dto.JwtResponse;
import unicore.api.dto.UserEmailCodeDto;
import unicore.api.service.AuthService;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> logIn(@RequestBody JwtRequest jwtRequest) {
        System.out.println(jwtRequest);
        return authService.logIn(jwtRequest);
    }

    @PostMapping("/signup/code")
    public ResponseEntity<JwtResponse> confirmEmail(@RequestBody ConfirmEmailDto confirmUserEmailDto) {
        return authService.confirmEmail(confirmUserEmailDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEmailCodeDto> signUp(@RequestBody JwtRequest jwtRequest) {
        return authService.signUp(jwtRequest);
    }
}