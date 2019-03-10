package com.core.kettle.api;

import com.core.kettle.service.KettleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KettleApi {
    @Autowired
    private KettleService api;
    @GetMapping
    String init(){
        api.submitTaskToKettle(1);
        return "haha";
    }
}
