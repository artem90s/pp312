package ru.kata.spring.boot_security.demo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable (name = "users_roles",
            joinColumns = @JoinColumn (name = "roles_id"),
            inverseJoinColumns = @JoinColumn (name = "user_id") )
    private List<User> users;
    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}