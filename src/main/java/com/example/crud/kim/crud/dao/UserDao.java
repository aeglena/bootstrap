package com.example.crud.kim.crud.dao;



import com.example.crud.kim.crud.model.User;

import java.util.List;

public interface UserDao {
    User findByUsername(String name);

    User readUser(Long id);

    List<User> readAll();

    void save(User user);

    User update(User user, Long id);

    void delete(Long id);
}
