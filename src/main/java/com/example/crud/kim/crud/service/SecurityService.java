package com.example.crud.kim.crud.service;//package com.security.bootspring.crudboot.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
