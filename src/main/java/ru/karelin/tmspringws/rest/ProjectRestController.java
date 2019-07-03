package ru.karelin.tmspringws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.karelin.tmspringws.dto.ProjectDto;
import ru.karelin.tmspringws.dto.ProjectLightDto;
import ru.karelin.tmspringws.dto.Result;
import ru.karelin.tmspringws.service.ProjectDtoService;


import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ProjectRestController {

    @Autowired
    ProjectDtoService projectDtoService;

    @GetMapping(value = "/lightProject", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProjectLightDto> getLightProjectList(HttpSession session) {
        return projectDtoService.findAllLightsByUserId((String) session.getAttribute("userId"));
    }


    @GetMapping(value = "/project", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<ProjectDto> getProjectList(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page, @RequestParam(name = "limit", required = false, defaultValue = "100") Integer limit, HttpSession session) {
        return projectDtoService.findAllByUserId((String) session.getAttribute("userId"), page - 1, limit);
    }


    @GetMapping(value = "/project/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectDto getProject(@PathVariable("id") String projectId, HttpSession session) {
        return projectDtoService.findByIdAndUserId(projectId, (String) session.getAttribute("userId"));
    }


    @PostMapping(value = "/project", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result createProject(@RequestBody ProjectDto project, HttpSession session) {
        try {
            String projectUserId = project.getUserId();
            if (projectUserId == null || projectUserId.isEmpty()) {
                project.setUserId((String) session.getAttribute("userId"));
            }
            projectDtoService.save(project);
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
        return new Result();
    }

    @PutMapping(value = "/project/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result editProject(@PathVariable("id") String projectId, @RequestBody ProjectDto project, HttpSession session) {
        projectDtoService.save(project);
        return new Result();
    }


    @DeleteMapping(value = "project/{id}")
    public Result removeProject(@PathVariable("id") String projectId, HttpSession session) {
        return new Result(projectDtoService.remove(projectId, (String) session.getAttribute("userId")));
    }
}
