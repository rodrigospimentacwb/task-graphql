package br.com.pimenta.tarefasGraphql.domains.users.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.pimenta.tarefasGraphql.domains.users.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    @Query("SELECT u FROM users u WHERE UPPER(u.email) = UPPER(:email)")
    User findByEmail(@Param("email") String email);

    List<User> findAll();
}
