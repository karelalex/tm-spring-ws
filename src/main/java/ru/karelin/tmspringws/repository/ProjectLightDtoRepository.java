package ru.karelin.tmspringws.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.karelin.tmspringws.dto.ProjectLightDto;

import java.util.List;

@Repository
public interface ProjectLightDtoRepository extends CrudRepository<ProjectLightDto, String> {
    List<ProjectLightDto> findAllByUserId(String id);
}
