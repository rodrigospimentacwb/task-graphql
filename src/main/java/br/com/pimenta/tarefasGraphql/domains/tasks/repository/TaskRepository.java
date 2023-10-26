package br.com.pimenta.tarefasGraphql.domains.tasks.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.pimenta.tarefasGraphql.domains.tasks.entity.Task;
import br.com.pimenta.tarefasGraphql.domains.tasks.enuns.TaskStatus;

@Repository
public interface TaskRepository extends CrudRepository<Task, UUID> {

    Optional<List<Task>> findTaskByUserId(UUID userId);

    List<Task> findAll();

    @Query("SELECT t FROM tasks t WHERE t.status = :status ")
    Optional<List<Task>> findByStatus(@Param("status") TaskStatus status);
}
