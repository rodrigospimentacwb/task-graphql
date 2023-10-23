package br.com.pimenta.tarefasGraphql.domains.users.service;

import br.com.pimenta.tarefasGraphql.domains.commons.exception.exceptions.NotFoundException;
import br.com.pimenta.tarefasGraphql.domains.commons.helper.PassCodeConverter;
import br.com.pimenta.tarefasGraphql.domains.users.entities.User;
import br.com.pimenta.tarefasGraphql.domains.users.repository.UserRepository;
import graphql.Assert;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Resource
    UserRepository repository;

    public Optional<User> findBydId(UUID uuid) {
        Assert.assertTrue(uuid != null, () -> "Id do usuário é inválido");
        return repository.findById(uuid);
    }

    public User createUser(String name, String email, String password) {
        validateUser(name, email, password);
        return repository.save(new User(name, email, PassCodeConverter.toMd5(password)));
    }

    private void validateUser(String name, String email, String password) {
        Assert.assertTrue(StringUtils.isNoneBlank(name), () -> "Nome é inválido");
        Assert.assertTrue(StringUtils.isNoneBlank(email), () -> "Formato do e-mail  é inválido");
        Assert.assertTrue(StringUtils.isNoneBlank(password), () -> "Password é inválido");
        Assert.assertTrue(repository.findByEmail(email) == null, () -> "Email invalido");
    }

    public UserDetails findByEmail(String username) {
        return repository.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) {
                return findByEmail(email);
            }
        };
    }
}
