package com.core.user.api;

import com.core.base.dto.Result;
import com.core.user.bean.UserDto;
import com.core.user.dao.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class UserApi {
    @Autowired
    private UserRespository userRespository;

    @GetMapping("/user/save")
    String save(UserDto user) {
        user.setAuditFlag("n");
        userRespository.save(user);
        return "注册成功了";
    }

    @GetMapping("/user/login")
    Result login(UserDto user) {
        Result result = new Result();
        if (null == userRespository.findByUserNameAndPassword(user.getUserName(), user.getPassword()) && null == userRespository.findByEmailAndPassword(user.getUserName(), user.getPassword())) {
            result.setCode(200);
            result.setMessage("登录失败");
        } else {
            result.setCode(200);
            result.setMessage("登录成功");
        }
        return result;
    }
}
