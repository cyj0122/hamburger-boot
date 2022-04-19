package org.hamburger.boot.example.clusterB.controller;

import lombok.extern.slf4j.Slf4j;
import org.hamburger.boot.core.dto.BaseResponse;
import org.hamburger.boot.example.clusterA.api.UserApi;
import org.hamburger.boot.example.clusterA.dto.User;
import org.hamburger.boot.example.clusterB.dto.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;

import java.io.IOException;

@RestController
@RequestMapping("/storage")
@Slf4j
public class StorageController {

    @Autowired
    private UserApi userApi;

    @GetMapping("/{id}")
    public BaseResponse buy(@PathVariable Integer id) {
        Storage storage = new Storage(id, 100L);
        storage.setCount(storage.getCount() - 1);

        Call<BaseResponse<User>> user = userApi.findUser("1");
        try {
            log.info("user {} buy, storage --",  user.execute().body());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BaseResponse.buildSuccess();
    }
}
