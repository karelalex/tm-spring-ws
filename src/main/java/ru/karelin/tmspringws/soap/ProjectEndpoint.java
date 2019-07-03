package ru.karelin.tmspringws.soap;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmspringws.dto.ProjectDto;
import ru.karelin.tmspringws.service.ProjectDtoService;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Component
public class ProjectEndpoint {

    @Autowired
    ProjectDtoService projectDtoService;

    @WebMethod
    public List<ProjectDto> getProjectList() {
        return projectDtoService.findAllByUserId(getCurrentUserId());
    }

    @WebMethod
    public ProjectDto getProjectById(
            @WebParam(name = "projectId") String id) {
        return projectDtoService.findByIdAndUserId(id, getCurrentUserId());
    }

    @WebMethod
    public void removeProjectById(
            @WebParam(name = "projectId") String id) {
        projectDtoService.remove(id, getCurrentUserId());
    }

    @WebMethod
    public void updateProject(
            @WebParam(name = "project") ProjectDto project
    ) {
        projectDtoService.save(project);
    }


    private String getCurrentUserId() {
        Message message = PhaseInterceptorChain.getCurrentMessage();
        HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
        HttpSession session = request.getSession(true);
        return (String) session.getAttribute("userId");
    }

}
