package br.com.pimenta.tarefasGraphql.domains.tasks.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.pimenta.tarefasGraphql.domains.tasks.entity.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, UUID> {

    Optional<List<Task>> findByUserId(UUID userId);
}
