package br.com.pimenta.tarefasGraphql.domains.tasks.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.pimenta.tarefasGraphql.domains.commons.exception.exceptions.ExecutionFailed;
import br.com.pimenta.tarefasGraphql.domains.commons.exception.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pimenta.tarefasGraphql.domains.tasks.entity.Task;
import br.com.pimenta.tarefasGraphql.domains.tasks.enuns.TaskStatus;
import br.com.pimenta.tarefasGraphql.domains.tasks.repository.TaskRepository;
import br.com.pimenta.tarefasGraphql.domains.users.service.UserService;
import graphql.Assert;
import jakarta.annotation.Resource;

@Service
public class TaskService {

    Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Resource
    TaskRepository repository;

    @Resource
    UserService userService;

    public Optional<Task> findBydId(UUID uuid) {
        Assert.assertTrue(uuid != null, () -> "UUID informado é inválido");
        return repository.findById(uuid);
    }

    public Optional<List<Task>> listTasks() {
        return Optional.ofNullable(repository.findAll());
    }

    public Optional<List<Task>> findByUserId(UUID userId) {
        Assert.assertTrue(userId != null, () -> "UserId informado é inválido");
        return repository.findTaskByUserId(userId);
    }

    public Optional<List<Task>> listTaskByStatus(TaskStatus status) {
        Assert.assertTrue(status != null, () -> "Status informado é inválido");
        return repository.findByStatus(status);
    }

    @Transactional
    public Task createTask(String description, UUID userId) {
        validateTask(userId);
        return repository.save(new Task(description, TaskStatus.NEW, userId));
    }

    private void validateTask(UUID userId) {
        Assert.assertTrue(userService.findBydId(userId).isPresent(), () -> "Usuário inválido");
    }

    @Transactional
    public Task updateTask(UUID uuid, TaskStatus status, String description) {
        Assert.assertTrue(uuid != null, () -> "Id informado é inválido");
        Assert.assertTrue(status != null, () -> "Status informado é inválido");
        try {
            return repository.findById(uuid)
                    .map(task -> {
                        task.setStatus(status);
                        task.setDescription(description);
                        return repository.save(task);
                    })
                    .orElseThrow(() -> {
                        logger.error("Falha ao tentar atualizar task uuid: " + uuid);
                        return new ExecutionFailed("Falha ao tentar atualizar a tarefa.");
                    });
        } catch (Exception ex) {
            logger.error("Falha ao tentar atualizar task uuid: " + uuid, ex);
            throw new ExecutionFailed("Falha ao tentar atualizar a tarefa.");
        }
    }

    @Transactional
    public void deleteTask(UUID uuid) {
        Assert.assertTrue(uuid != null, () -> "Id informado é inválido");
        Task task = repository.findById(uuid)
                .filter(t -> t != null).orElseThrow(() -> new NotFoundException("Nenhuma tarefa encontrada"));
        repository.delete(task);
    }
}
