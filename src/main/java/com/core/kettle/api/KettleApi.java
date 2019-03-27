package com.core.kettle.api;

import com.core.kettle.bean.SysConfigJob;
import com.core.kettle.bean.SysDbConnection;
import com.core.kettle.dao.SysConfigJobResitory;
import com.core.kettle.dao.SysDbConnectionRespository;
import com.core.kettle.service.KettleService;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.RowMetaAndData;
import org.pentaho.di.core.RowSet;
import org.pentaho.di.core.exception.KettleStepException;
import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.RowListener;
import org.pentaho.di.trans.step.StepInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class KettleApi {
    @Autowired
    private KettleService api;
    @Autowired
    private SysConfigJobResitory sysConfigJobResitory;
    @Autowired
    private SysDbConnectionRespository sysDbConnectionRespository;
    @GetMapping("/init")
    String init()throws Exception{
        KettleEnvironment.init();
        SysConfigJob job=sysConfigJobResitory.findByIdAndStatus(201,"Y");
        if(job.getStatus().equalsIgnoreCase("N")){
            return "已经执行过了";
        }
        TransMeta transMeta = new TransMeta();
        transMeta.setName(job.getJobName());
        SysDbConnection dbfrom=sysDbConnectionRespository.findById(Integer.parseInt(job.getDbFrom())).get();
        SysDbConnection dbTo=sysDbConnectionRespository.findById(Integer.parseInt(job.getDbTo())).get();
        long start=System.currentTimeMillis();
            try{
                api.submitTaskToKettle(transMeta,job,dbfrom,dbTo);
                Trans trans = new Trans(transMeta);
                trans.prepareExecution(null);

                trans.startThreads();
                trans.waitUntilFinished();

                if(trans.getErrors() > 0) {
                    System.out.println(">>>>>>>>>> ERROR");
                }else {
                    System.out.println(">>>>>>>>>> Row size ");
                }
            }catch (Exception e){
                System.out.println("出错了"+e);
            }

        long end=System.currentTimeMillis();
        System.out.println("#################################################");
        System.out.println("执行花费了?:"+(end-start));
        return "数据库同步成功了";
    }
    @GetMapping("/initByFile")
    public String runTransformation(String filename)throws Exception {
        api.submitTaskToKettleByFile(filename);
        return "执行成功了";
    }

}
