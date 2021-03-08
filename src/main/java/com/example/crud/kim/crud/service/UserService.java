package com.example.crud.kim.crud.service;



import com.example.crud.kim.crud.model.Role;
import com.example.crud.kim.crud.model.User;

import java.util.List;

public interface UserService {
    User readUser(Long id);

    List<User> readAll();

    void save(User user);

    User update(User user, Long id);

    void delete(Long id);

    Role getRoleByName(String name);

    User findByUsername(String username);
}
