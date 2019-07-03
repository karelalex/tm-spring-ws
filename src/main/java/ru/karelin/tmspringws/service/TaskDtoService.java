package ru.karelin.tmspringws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karelin.tmspringws.dto.TaskDto;
import ru.karelin.tmspringws.entity.Task;
import ru.karelin.tmspringws.repository.TaskDtoRepository;
import ru.karelin.tmspringws.repository.TaskRepository;


import java.util.List;

@Service
@Transactional
public class TaskDtoService {


    @Autowired
    private TaskDtoRepository taskDtoRepository;

    @Autowired
    private TaskRepository taskRepository;

    public TaskDto findByIdAndUserId(String id, String userId) {
        return taskDtoRepository.findByIdAndUserId(id, userId);
    }

    public List<TaskDto> findAllByUserId(String userId) {
        return taskDtoRepository.findAllByUserId(userId);
    }

    public Page<TaskDto> findAllByUserId(String userId, int page, int limit) {
        if(userId == null|| userId.isEmpty()) return Page.empty(PageRequest.of(page, limit));
        return taskDtoRepository.findAllByUserId(userId, PageRequest.of(page, limit));
    }

    public Page<TaskDto> findAllByUserIdAndProjectId(String userId, String projectId, int page, int limit) {
        if(userId == null|| userId.isEmpty()) return Page.empty(PageRequest.of(page, limit));
        return taskDtoRepository.findAllByUserIdAndProjectId(userId, projectId, PageRequest.of(page, limit));
    }
    public List<TaskDto> findAllByUserIdAndProjectId(String userId, String projectId) {
        return taskDtoRepository.findAllByUserIdAndProjectId(userId, projectId);
    }

    public void save(TaskDto task) {
        taskDtoRepository.save(task);
    }

    public boolean remove(String id, String userId) {
        Task task = taskRepository.findByIdAndUserId(id, userId);
        if (task == null)return false;
        else taskRepository.delete(task);
        return true;
    }
}
