package ru.karelin.tmspringws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.karelin.tmspringws.dto.Result;
import ru.karelin.tmspringws.dto.TaskDto;
import ru.karelin.tmspringws.service.TaskDtoService;

import javax.servlet.http.HttpSession;

@RestController
public class TaskRestController {

    @Autowired
    TaskDtoService taskDtoService;


    @GetMapping(value = "/task/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto getTask(@PathVariable("id") String taskId, HttpSession session) {
        return taskDtoService.findByIdAndUserId(taskId, (String) session.getAttribute("userId"));
    }


    @GetMapping(value = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<TaskDto> getTaskList(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page, @RequestParam(name = "limit", required = false, defaultValue = "100") Integer limit, @RequestParam(value = "projectId", required = false, defaultValue = "") String projectId, HttpSession session) {
        if (projectId.isEmpty())
            return taskDtoService.findAllByUserId((String) session.getAttribute("userId"), page - 1, limit);
        return taskDtoService.findAllByUserIdAndProjectId(((String) session.getAttribute("userId")), projectId, page - 1, limit);
    }


    @PostMapping(value = "/task", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result createTask(@RequestBody TaskDto task, HttpSession session) {
        String taskUserId = task.getUserId();
        if (taskUserId == null || taskUserId.isEmpty()) {
            task.setUserId((String) session.getAttribute("userId"));
        }
        taskDtoService.save(task);
        return new Result();
    }


    @PutMapping(value = "/task/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result editTask(@PathVariable("id") String taskId, @RequestBody TaskDto task, HttpSession session) {
        taskDtoService.save(task);
        return new Result();
    }

    @DeleteMapping(value = "/task/{id}")
    public Result removeTask(@PathVariable("id") String taskId, HttpSession session) {
        return new Result(taskDtoService.remove(taskId, (String) session.getAttribute("userId")));
    }
}
