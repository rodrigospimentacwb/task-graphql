package br.com.pimenta.tarefasGraphql.domains.tasks.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import br.com.pimenta.tarefasGraphql.domains.commons.exception.exceptions.NotFoundException;
import br.com.pimenta.tarefasGraphql.domains.tasks.entity.Task;
import br.com.pimenta.tarefasGraphql.domains.tasks.enuns.TaskStatus;
import br.com.pimenta.tarefasGraphql.domains.tasks.service.TaskService;
import br.com.pimenta.tarefasGraphql.domains.users.entity.User;
import br.com.pimenta.tarefasGraphql.domains.users.service.UserService;
import jakarta.annotation.Resource;

@Controller
public class TaskController {

    @Resource
    TaskService service;

    @Resource
    UserService userService;

    @QueryMapping
    public Task taskById(@Argument UUID uuid) {
        return service.findBydId(uuid).orElseThrow(()
                -> new NotFoundException("Tarefa não encontrada com ID: " + uuid));
    }

    @QueryMapping
    public List<Task> findTasksByUserId(@Argument UUID userId) {
        return service.findByUserId(userId)
                .filter(tasks -> !tasks.isEmpty()).orElseThrow(() -> new NotFoundException("Nenhuma tarefa encontrada para o usuário"));
    }

    @QueryMapping
    public List<Task> listAllTasks() {
        return service.listTasks().filter(tasks -> !tasks.isEmpty()).orElseThrow(() -> new NotFoundException("Nenhuma tarefa encontrado"));
    }

    @QueryMapping
    public List<Task> listTaskByStatus(@Argument TaskStatus taskStatus) {
        return service.listTaskByStatus(taskStatus).filter(tasks -> !tasks.isEmpty()).orElseThrow(() -> new NotFoundException("Nenhuma tarefa" +
                " encontrado com status: " + taskStatus));
    }

    @MutationMapping
    public Task createTask(@Argument String description, @Argument UUID userId) {
        return service.createTask(description, userId);
    }

    @MutationMapping
    public Task updateTask(@Argument UUID uuid, @Argument TaskStatus taskStatus, @Argument String description) {
        return service.updateTask(uuid, taskStatus, description);
    }

    @MutationMapping
    public String deleteTask(@Argument UUID uuid) {
        service.deleteTask(uuid);
        return "Tarefa excluída com sucesso";
    }

    @SchemaMapping
    public User user(Task task) {
        return userService.findBydId(task.getUserId()).orElseThrow(()
                -> new NotFoundException("Usuário da task não"));
    }
}
