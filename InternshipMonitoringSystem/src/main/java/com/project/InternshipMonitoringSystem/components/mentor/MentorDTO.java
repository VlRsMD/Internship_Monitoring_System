package com.project.InternshipMonitoringSystem.components.mentor;

import java.util.List;

public class MentorDTO {
    private Long id;
    private String name;
    private List<Long> projectIds;

    public MentorDTO(Long id, String name, List<Long> projectIds) {
        this.id = id;
        this.name = name;
        this.projectIds = projectIds;
    }

    public MentorDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(List<Long> projectIds) {
        this.projectIds = projectIds;
    }
}
