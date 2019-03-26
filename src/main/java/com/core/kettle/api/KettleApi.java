package com.core.kettle.api;

import com.core.kettle.bean.SysConfigJob;
import com.core.kettle.bean.SysDbConnection;
import com.core.kettle.dao.SysConfigJobResitory;
import com.core.kettle.dao.SysDbConnectionRespository;
import com.core.kettle.service.KettleService;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
        SysConfigJob job=sysConfigJobResitory.findByIdAndStatus(1,"Y");
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
                trans.execute(null);
                trans.waitUntilFinished();
                job.setDateUpdate(new Date());
                job.setStatus("N");
                if (trans.getErrors() > 0) {
                    job.setStatus("Y ");
                    throw new RuntimeException("数据库同步失败");
                }
                sysConfigJobResitory.save(job);
                System.out.println("***********the end************");
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
