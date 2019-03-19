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

        long start=System.currentTimeMillis();
        for (int i=1;i<=201;i++){
            try{
                api.submitTaskToKettle(i);
            }catch (Exception e){
                System.out.println("出错了"+e);
            }

        }
        long end=System.currentTimeMillis();
        System.out.println("#################################################");
        System.out.println("#################################################");
        System.out.println("#################################################");
        System.out.println("#################################################");
        System.out.println(end-start);
        return "haha";
    }
}
