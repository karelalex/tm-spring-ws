package ru.karelin.tmspringws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.karelin.tmspringws.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

    Project findByIdAndUserId(String id, String userId);
}
