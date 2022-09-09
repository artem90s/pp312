package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;

public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    RoleServiceImpl (RoleRepository roleRepository){
        this.roleRepository=roleRepository;
    }

    @Override
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }
}
