package br.com.pimenta.tarefasGraphql.domains.users.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import br.com.pimenta.tarefasGraphql.domains.commons.exception.exceptions.NotFoundException;
import br.com.pimenta.tarefasGraphql.domains.users.entity.User;
import br.com.pimenta.tarefasGraphql.domains.users.service.UserService;
import jakarta.annotation.Resource;

@Controller
public class UserController {

    @Resource
    UserService service;

    @QueryMapping
    public List<User> listUsers() {
        return service.listUsers().filter(users -> !users.isEmpty()).orElseThrow(() -> new NotFoundException("Nenhum usuário encontrado"));
    }

    @QueryMapping
    public User userById(@Argument UUID uuid) {
        return service.findBydId(uuid).orElseThrow(()
                -> new NotFoundException("Usuário não encontrada com ID: " + uuid));
    }

    @MutationMapping
    public User createUser(@Argument String name, @Argument String email) {
        return service.createUser(name, email);
    }
}
