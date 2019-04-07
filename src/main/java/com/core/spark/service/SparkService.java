package com.core.spark.service;

import com.core.kettle.bean.SysDbConnection;

public interface SparkService {
    boolean fullImport(SysDbConnection dbFrom, SysDbConnection dbTo);
    // boolean deltaImport(SysDbConnection dbFrom,SysDbConnection dbTo);
}
