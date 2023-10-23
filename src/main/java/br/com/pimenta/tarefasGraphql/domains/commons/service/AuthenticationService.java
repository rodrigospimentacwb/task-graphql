package br.com.pimenta.tarefasGraphql.domains.commons.service;

import br.com.pimenta.tarefasGraphql.domains.commons.dao.request.SignUpRequest;
import br.com.pimenta.tarefasGraphql.domains.commons.dao.request.SigninRequest;
import br.com.pimenta.tarefasGraphql.domains.commons.dao.response.JwtAuthenticationResponse;
import br.com.pimenta.tarefasGraphql.domains.users.entities.User;
import br.com.pimenta.tarefasGraphql.domains.users.enuns.Role;
import br.com.pimenta.tarefasGraphql.domains.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signup(SignUpRequest request) {

        var user = User.builder().name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();

        userRepository.save(user);
        var jwt = jwtService.generateToken(user);

        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public JwtAuthenticationResponse signin(SigninRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);

        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
