package com.eco.monitor.entity;

import javax.persistence.*;

@Entity
@Table(name = "scopes")
public class Scope {

    @Id
    @Column(updatable = false, nullable = false)
    private String scopeId;

    private String name;

    public Scope() {
    }

    public Scope(String scopeId) {
        this.scopeId = scopeId;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
