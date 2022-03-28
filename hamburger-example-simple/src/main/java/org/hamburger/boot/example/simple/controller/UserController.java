package org.hamburger.boot.example.simple.controller;

import org.hamburger.boot.core.dto.BaseResponse;
import org.hamburger.boot.core.exception.HamException;
import org.hamburger.boot.example.simple.common.ResultCode;
import org.hamburger.boot.example.simple.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user/{id}")
    public BaseResponse<User> getUser(@PathVariable("id") String id) {
        return BaseResponse.of(new User(id, "name"));
    }

    @GetMapping("/user/error")
    public BaseResponse<User> error() {
        throw new HamException(ResultCode.SYS_ERROR);
    }
}
