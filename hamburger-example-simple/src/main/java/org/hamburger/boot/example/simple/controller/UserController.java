package org.hamburger.boot.example.simple.controller;

import lombok.extern.slf4j.Slf4j;
import org.hamburger.boot.core.dto.BaseResponse;
import org.hamburger.boot.core.exception.HamException;
import org.hamburger.boot.example.simple.common.ResultCode;
import org.hamburger.boot.example.simple.dto.User;
import org.hamburger.boot.example.simple.service.IUserService;
import org.hamburger.boot.starter.context.GlobalContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/user/{id}")
    public BaseResponse<User> getUser(@PathVariable("id") String id) {
        return BaseResponse.of(new User(id, "name"));
    }

    @GetMapping("/user/error")
    public BaseResponse<User> error() {
        throw new HamException(ResultCode.SYS_ERROR);
    }

    @PostMapping("/user")
    public BaseResponse addUser(@RequestBody @Valid User user) {
        return BaseResponse.buildSuccess();
    }

    @PostMapping("/user/async")
    public BaseResponse addUserAsync(@RequestBody @Valid User user) {
        log.info("http header: userId: {}", GlobalContext.get("userId"));
        userService.asyncAddUser(user);
        return BaseResponse.buildSuccess();
    }
}
