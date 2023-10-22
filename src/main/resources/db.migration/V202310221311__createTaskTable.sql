CREATE TABLE tasks (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    description VARCHAR(1000),
    status VARCHAR(20) NOT NULL,
    userId uuid,
    FOREIGN KEY (userId) REFERENCES "users" (id)
);

CREATE INDEX idx_tasks_status ON tasks(status);

