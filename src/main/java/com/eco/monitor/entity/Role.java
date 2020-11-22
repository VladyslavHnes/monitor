package com.eco.monitor.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinTable(name = "role_scopes",
            joinColumns = @JoinColumn(
                    name = "roleId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "scopeId", referencedColumnName = "scopeId"))
    private Set<Scope> scopeSet = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Scope> getScopeSet() {
        return scopeSet;
    }
}
