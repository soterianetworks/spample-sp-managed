package com.soterianetworks.spample.domain.model;


import com.soterianetworks.spase.domain.model.AbstractBenityAware;
import com.soterianetworks.spase.domain.model.Department;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "WMS_WORKSHOP")
public class Workshop extends AbstractBenityAware {

    @NotNull
    @Column(name = "NAME",nullable = false)
    private String name;

    @NotNull
    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "MANAGER")
    private String manager;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

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

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
