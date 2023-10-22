package br.com.pimenta.tarefasGraphql.domains.tasks.repository;

import br.com.pimenta.tarefasGraphql.domains.tasks.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends CrudRepository<Task, UUID> {
}
