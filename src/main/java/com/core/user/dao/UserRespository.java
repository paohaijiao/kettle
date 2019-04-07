package com.core.user.dao;

import com.core.user.bean.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<UserDto, Integer> {
    UserDto findByUserNameAndPassword(String userName, String password);

    UserDto findByEmailAndPassword(String email, String password);
}
