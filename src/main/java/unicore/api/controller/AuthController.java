package unicore.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import unicore.api.api.AuthApi;
import unicore.api.dto.ConfirmEmailDto;
import unicore.api.dto.JwtRequest;
import unicore.api.dto.JwtResponse;
import unicore.api.dto.UserEmailCodeDto;
import unicore.api.service.impl.AuthServiceImpl;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {
    private final AuthServiceImpl authService;

    public ResponseEntity<JwtResponse> logIn(@RequestBody JwtRequest jwtRequest) {
        System.out.println(jwtRequest);
        return authService.logIn(jwtRequest);
    }

    public ResponseEntity<JwtResponse> confirmEmail(@RequestBody ConfirmEmailDto confirmUserEmailDto) {
        return authService.confirmEmail(confirmUserEmailDto);
    }

    public ResponseEntity<UserEmailCodeDto> signUp(@RequestBody JwtRequest jwtRequest) {
        return authService.signUp(jwtRequest);
    }
}