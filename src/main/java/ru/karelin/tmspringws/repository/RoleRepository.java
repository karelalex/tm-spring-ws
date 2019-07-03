package ru.karelin.tmspringws.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.karelin.tmspringws.entity.Role;


@Repository
public interface RoleRepository extends CrudRepository<Role, String> {

}
