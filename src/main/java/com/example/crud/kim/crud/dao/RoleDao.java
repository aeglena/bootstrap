package com.example.crud.kim.crud.dao;


import com.example.crud.kim.crud.model.Role;

public interface RoleDao {
    Role getById(Long id);
    Role getRoleByName(String name);
}
