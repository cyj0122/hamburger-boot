package org.hamburger.boot.example.simple.service;

import org.hamburger.boot.example.simple.dto.User;

public interface IUserService {

    void asyncAddUser(User user);
}
