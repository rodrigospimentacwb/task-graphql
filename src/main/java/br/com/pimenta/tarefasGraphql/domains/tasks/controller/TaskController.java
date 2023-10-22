package br.com.pimenta.tarefasGraphql.domains.tasks.controller;

import br.com.pimenta.tarefasGraphql.domains.commons.exception.exceptions.NotFoundException;
import br.com.pimenta.tarefasGraphql.domains.tasks.model.Task;
import br.com.pimenta.tarefasGraphql.domains.tasks.service.TaskService;
import br.com.pimenta.tarefasGraphql.domains.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class TaskController {

    @Autowired
    TaskService service;

    @QueryMapping
    public Task taskById(@Argument UUID uuid) {
        return service.findBydId(uuid).orElseThrow(()
                -> new NotFoundException("Tarefa não encontrada com ID: " + uuid));
    }

    @MutationMapping
    public Task createTask(@Argument String description, @Argument UUID userId) {
        return service.createTask(description, userId);
    }
}
