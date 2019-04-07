package com.core.kettle.api;

import com.core.kettle.bean.SysDbConnection;
import com.core.kettle.dao.SysDbConnectionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.Date;

@RestController()
public class DbApi {
    @Autowired
    private SysDbConnectionRespository sysDbConnectionRespository;

    @GetMapping("/db/getAllDb")
    Page<SysDbConnection> getAllDb(int page, int size) throws Exception {
        Page<SysDbConnection> dbList = sysDbConnectionRespository.findAll(new PageRequest(page, size, Sort.Direction.ASC, "id"));
        return dbList;
    }

    @GetMapping("/db/save")
    String save(SysDbConnection db) throws Exception {
        db.setCreateBy("sys");
        db.setUpdateBy("sys");
        db.setDateCreate(new Date());
        db.setDateUpdate(new Date());
        sysDbConnectionRespository.save(db);
        return "保存成功了";
    }

    @GetMapping("/db/delete")
    String delete(SysDbConnection db) throws Exception {
        sysDbConnectionRespository.deleteById(db.getId());
        return "删除成功了";
    }

    @GetMapping("/db/queryById")
    SysDbConnection findById(SysDbConnection db) throws Exception {
        return sysDbConnectionRespository.findById(db.getId()).get();
    }
}
