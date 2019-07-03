package ru.karelin.tmspringws.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import ru.karelin.tmspringws.dto.TaskDto;


import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface TaskDtoRepository extends JpaRepository<TaskDto, String> {
    List<TaskDto> findAllByUserIdAndProjectId(String userId, String projectId);

    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
    Page<TaskDto> findAllByUserIdAndProjectId(String userId, String projectId, Pageable pageable);

    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
    TaskDto findByIdAndUserId(String id, String userId);

    List<TaskDto> findAllByUserId(String userId);
    Page<TaskDto> findAllByUserId(String userId, Pageable pageable);

}
