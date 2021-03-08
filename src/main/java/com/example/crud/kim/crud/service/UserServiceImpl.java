package com.example.crud.kim.crud.service;

import com.example.crud.kim.crud.dao.RoleDao;
import com.example.crud.kim.crud.dao.UserDao;
import com.example.crud.kim.crud.model.Role;
import com.example.crud.kim.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }

    @Override
    public Role getRoleByName(String name){
        return roleDao.getRoleByName(name);
    }

    @Override
    public User readUser(Long id) {
        return userDao.readUser(id);
    }

    @Override
    public List<User> readAll() {
        return userDao.readAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public User update(User user, Long id) {
        return userDao.update(user, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }
}
