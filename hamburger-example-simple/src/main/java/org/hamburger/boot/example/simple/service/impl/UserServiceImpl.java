package org.hamburger.boot.example.simple.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.hamburger.boot.example.simple.dto.User;
import org.hamburger.boot.example.simple.service.IUserService;
import org.hamburger.boot.starter.context.GlobalContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Override
    @Async
    public void asyncAddUser(User user) {
        log.info("thread name {}, user:{} BY userId: {}", Thread.currentThread().getName(), user, GlobalContext.get("userId"));
    }
}
