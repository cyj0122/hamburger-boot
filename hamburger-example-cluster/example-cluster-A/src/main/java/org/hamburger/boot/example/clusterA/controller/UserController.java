package org.hamburger.boot.example.clusterA.controller;

import org.hamburger.boot.core.dto.BaseResponse;
import org.hamburger.boot.example.clusterA.api.UserApi;
import org.hamburger.boot.example.clusterA.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{id}")
    public BaseResponse<User> findUser(@PathVariable("id") String id) {
       return BaseResponse.of(new User(id, "name"));
    }
}
