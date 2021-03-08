package com.example.crud.kim.crud.service;


import com.example.crud.kim.crud.model.Role;

public interface RoleService {
    void add(Role role);
    void update(Role role, Long id);
    void delete(Long id);
    Role read(Long id);
}
