package br.com.pimenta.tarefasGraphql.domains.users.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pimenta.tarefasGraphql.domains.users.entity.User;
import br.com.pimenta.tarefasGraphql.domains.users.repository.UserRepository;
import graphql.Assert;
import jakarta.annotation.Resource;

@Service
public class UserService {

    @Resource
    UserRepository repository;

    public Optional<User> findBydId(UUID uuid) {
        Assert.assertTrue(uuid != null, () -> "Id do usuário é inválido");
        return repository.findById(uuid);
    }

    public Optional<List<User>> listUsers() {
        return Optional.ofNullable(repository.findAll());
    }

    @Transactional
    public User createUser(String name, String email) {
        validateUser(name, email);
        return repository.save(new User(name, email));
    }

    private void validateUser(String name, String email) {
        Assert.assertTrue(StringUtils.isNoneBlank(name), () -> "Nome é inválido");
        Assert.assertTrue(StringUtils.isNoneBlank(email), () -> "Formato do e-mail  é inválido");
        Assert.assertTrue(repository.findByEmail(email) == null, () -> "Email invalido");
    }
}
