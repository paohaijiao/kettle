package com.core.spark.service.impl;

import com.core.kettle.bean.SysDbConnection;
import com.core.spark.service.SparkService;
import org.springframework.stereotype.Service;

@Service("sparkService")
public class SparkServiceImpl implements SparkService {
    @Override
    public boolean fullImport(SysDbConnection dbFrom, SysDbConnection dbTo) {
        return true;
    }
}
