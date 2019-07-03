package ru.karelin.tmspringws.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.karelin.tmspringws.entity.AbstractEntity;
import ru.karelin.tmspringws.enumeration.Status;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task")
public class TaskDto extends AbstractEntity {
    private String name;

    private String description;

    @Column(name = "startingDate")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date startingDate;

    @Column(name = "finishDate")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date finishDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "project_id")
    private String  projectId;

    @Column(name = "user_id")
    private String userId;


    //getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
