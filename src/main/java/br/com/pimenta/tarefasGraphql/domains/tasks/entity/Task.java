package br.com.pimenta.tarefasGraphql.domains.tasks.entity;

import java.util.UUID;

import br.com.pimenta.tarefasGraphql.domains.tasks.enuns.TaskStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tasks")
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID uuid;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @Column(name = "userid")
    private UUID userId;

    public Task(String description, TaskStatus status, UUID userId) {
        this.description = description;
        this.status = status;
        this.userId = userId;
    }
}
