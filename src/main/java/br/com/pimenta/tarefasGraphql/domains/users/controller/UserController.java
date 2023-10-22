package br.com.pimenta.tarefasGraphql.domains.users.controller;

import br.com.pimenta.tarefasGraphql.domains.commons.exception.exceptions.NotFoundException;
import br.com.pimenta.tarefasGraphql.domains.users.model.User;
import br.com.pimenta.tarefasGraphql.domains.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    UserService service;

    @QueryMapping
    public User userById(@Argument UUID uuid) {
        return service.findBydId(uuid).orElseThrow(()
                -> new NotFoundException("Usuário não encontrada com ID: " + uuid));
    }

    @MutationMapping
    public User createUser(@Argument String name, @Argument String email, @Argument String password) {
        return service.createUser(name, email, password);
    }
}
