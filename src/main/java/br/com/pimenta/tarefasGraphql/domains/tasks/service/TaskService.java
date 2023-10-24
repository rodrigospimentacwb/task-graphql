package br.com.pimenta.tarefasGraphql.domains.tasks.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pimenta.tarefasGraphql.domains.tasks.enuns.TaskStatus;
import br.com.pimenta.tarefasGraphql.domains.tasks.entity.Task;
import br.com.pimenta.tarefasGraphql.domains.tasks.repository.TaskRepository;
import br.com.pimenta.tarefasGraphql.domains.users.service.UserService;
import graphql.Assert;
import jakarta.annotation.Resource;

@Service
public class TaskService {

    @Resource
    TaskRepository repository;

    @Resource
    UserService userService;

    public Optional<Task> findBydId(UUID uuid) {
        Assert.assertTrue(uuid != null, () -> "UUID informado é inválido");
        return repository.findById(uuid);
    }

    public Optional<List<Task>> findByUserId(UUID userId) {
        Assert.assertTrue(userId != null, () -> "UserId informado é inválido");
        return repository.findByUserId(userId);
    }

    @Transactional
    public Task createTask(String description, UUID userId) {
        validateTask(userId);
        return repository.save(new Task(description, TaskStatus.NEW, userId));
    }

    private void validateTask(UUID userId) {
        Assert.assertTrue(userService.findBydId(userId).isPresent(), () -> "Usuário inválido");
    }
}
