package com.core.kettle.dao;

import com.core.kettle.bean.SysDbConnection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysDbConnectionRespository extends JpaRepository<SysDbConnection,Integer> {
}
