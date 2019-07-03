package ru.karelin.tmspringws.service;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karelin.tmspringws.dto.ProjectDto;
import ru.karelin.tmspringws.dto.ProjectLightDto;
import ru.karelin.tmspringws.entity.Project;
import ru.karelin.tmspringws.repository.ProjectDtoRepository;
import ru.karelin.tmspringws.repository.ProjectLightDtoRepository;
import ru.karelin.tmspringws.repository.ProjectRepository;


import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ProjectDtoService {
    @Autowired
    private ProjectDtoRepository projectDtoRepository;

    @Autowired
    private ProjectLightDtoRepository projectLightDtoRepository;

    @Autowired
    private ProjectRepository projectRepository;


    public void save(ProjectDto project) {
        if (project == null) return;
        projectDtoRepository.save(project);
    }

    public boolean remove(@Nullable String projectId, String userId) {
        if (projectId == null || projectId.isEmpty()) return false;
        Project project = projectRepository.findByIdAndUserId(projectId, userId);
        if (project == null) {
            return false;
        } else {
            projectRepository.delete(project);
            return true;
        }
    }

    public Page<ProjectDto> findAllByUserId(String userId, int page, int limit) {
        if (userId == null || userId.isEmpty()) return Page.empty(PageRequest.of(page, limit));
        return projectDtoRepository.findAllByUserId(userId, PageRequest.of(page, limit));
    }

    public ProjectDto findByIdAndUserId(String id, String userId) {
        if (id == null || userId == null || id.isEmpty() || userId.isEmpty()) return null;
        return projectDtoRepository.findByIdAndUserId(id, userId);
    }

    public List<ProjectLightDto> findAllLightsByUserId(String userId) {
        return projectLightDtoRepository.findAllByUserId(userId);
    }

    public List<ProjectDto> findAllByUserId(String userId) {
        if (userId == null || userId.isEmpty()) return Collections.emptyList();
        return projectDtoRepository.findAllByUserId(userId);
    }
}
