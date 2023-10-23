package br.com.pimenta.tarefasGraphql.domains.commons.controller;

import br.com.pimenta.tarefasGraphql.domains.commons.dao.request.SignUpRequest;
import br.com.pimenta.tarefasGraphql.domains.commons.dao.request.SigninRequest;
import br.com.pimenta.tarefasGraphql.domains.commons.dao.response.JwtAuthenticationResponse;
import br.com.pimenta.tarefasGraphql.domains.commons.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
