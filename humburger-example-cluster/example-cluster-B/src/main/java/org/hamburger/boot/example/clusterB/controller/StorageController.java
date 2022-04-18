package org.hamburger.boot.example.clusterB.controller;

import lombok.extern.slf4j.Slf4j;
import org.hamburger.boot.core.dto.BaseResponse;
import org.hamburger.boot.example.clusterB.dto.Storage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage")
@Slf4j
public class StorageController {

    @GetMapping("/{id}")
    public BaseResponse buy(@PathVariable Integer id) {
        Storage storage = new Storage(id, 100L);
        storage.setCount(storage.getCount() - 1);
        log.info("storage --");
        return BaseResponse.buildSuccess();
    }
}
