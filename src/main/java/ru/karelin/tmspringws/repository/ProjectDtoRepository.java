package ru.karelin.tmspringws.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import ru.karelin.tmspringws.dto.ProjectDto;


import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface ProjectDtoRepository extends JpaRepository<ProjectDto, String> {

    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
    ProjectDto findByIdAndUserId(String id, String userId);

    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
    Page<ProjectDto> findAllByUserId(String id, Pageable pageable);

    List<ProjectDto> findAllByUserId(String userId);
}
