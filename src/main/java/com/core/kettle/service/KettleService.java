package com.core.kettle.service;

import com.core.kettle.bean.SysConfigJob;
import com.core.kettle.bean.SysDbConnection;
import org.pentaho.di.trans.TransMeta;

public interface KettleService {
     boolean submitTaskToKettle(TransMeta transMeta, SysConfigJob job, SysDbConnection dbfrom , SysDbConnection dbTo);
     boolean submitTaskToKettleByFile(String file) throws Exception ;
}
