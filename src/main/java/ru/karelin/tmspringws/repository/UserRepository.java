package ru.karelin.tmspringws.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.karelin.tmspringws.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByLogin(String login);

}
