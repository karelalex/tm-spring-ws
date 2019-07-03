package ru.karelin.tmspringws.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.karelin.tmspringws.entity.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, String> {

    Task findByIdAndUserId(String id, String userId);


}
