package com.monojit.jobms.Job.external;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
public class Company {
    private Long id;
    private String name;
    private String description;

    public Company() {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

