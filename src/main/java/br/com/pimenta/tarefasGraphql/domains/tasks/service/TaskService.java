package br.com.pimenta.tarefasGraphql.domains.tasks.service;

import br.com.pimenta.tarefasGraphql.domains.tasks.enuns.TaskStatus;
import br.com.pimenta.tarefasGraphql.domains.tasks.model.Task;
import br.com.pimenta.tarefasGraphql.domains.tasks.repository.TaskRepository;
import br.com.pimenta.tarefasGraphql.domains.users.service.UserService;
import graphql.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    TaskRepository repository;

    @Autowired
    UserService userService;

    public Optional<Task> findBydId(UUID uuid) {
        Assert.assertTrue(uuid != null, () -> "UUID informado é inválido");
        return repository.findById(uuid);
    }

    public Task createTask(String description, UUID userId) {
        validateTask(userId);
        return repository.save(new Task(description, TaskStatus.NEW, userId));
    }

    private void validateTask(UUID userId) {
        Assert.assertTrue(userService.findBydId(userId).isPresent(), () -> "Usuário inválido");
    }
}
