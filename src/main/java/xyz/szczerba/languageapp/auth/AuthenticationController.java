package xyz.szczerba.languageapp.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.szczerba.languageapp.auth.model.AuthenticationRequest;
import xyz.szczerba.languageapp.auth.model.AuthenticationResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/authenticate")
class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    ResponseEntity<AuthenticationResponse> createToken(@RequestBody @Valid AuthenticationRequest authRequest) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
            var user = (UserDetails) auth.getPrincipal();

            String token = tokenService.generateNewToken(user);
            System.out.println(token);
            return ResponseEntity.ok(new AuthenticationResponse(token));

        }catch (BadCredentialsException e){
            throw new IllegalArgumentException("Password is invalid");
        }

    }
}
