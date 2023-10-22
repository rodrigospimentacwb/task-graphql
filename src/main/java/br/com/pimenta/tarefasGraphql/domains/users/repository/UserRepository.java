package br.com.pimenta.tarefasGraphql.domains.users.repository;

import br.com.pimenta.tarefasGraphql.domains.users.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    @Query("SELECT u FROM users u WHERE UPPER(u.email) = UPPER(:email)")
    User findByEmail(@Param("email") String email);
}
