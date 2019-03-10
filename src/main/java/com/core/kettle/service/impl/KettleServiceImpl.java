package com.core.kettle.service.impl;

import com.core.kettle.bean.SysConfigJob;
import com.core.kettle.bean.SysDbConnection;
import com.core.kettle.dao.SysConfigJobResitory;
import com.core.kettle.dao.SysDbConnectionRespository;
import com.core.kettle.service.KettleService;
import org.pentaho.di.core.KettleEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.logging.StepLogTable;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.core.variables.VariableSpace;
import org.pentaho.di.core.variables.Variables;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.steps.insertupdate.InsertUpdateMeta;
import org.pentaho.di.trans.steps.tableinput.TableInputMeta;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Service
public class KettleServiceImpl implements KettleService {
    @Autowired
    private SysConfigJobResitory sysConfigJobResitory;
    @Autowired
    private SysDbConnectionRespository sysDbConnectionRespository;
    public static String kettle_log = "t_lzfx_data_log";


    @Override
    public boolean submitTaskToKettle(Integer id) {
        try {
            SysConfigJob job=sysConfigJobResitory.findById(id).get();
            SysDbConnection dbfrom=sysDbConnectionRespository.findById(Integer.parseInt(job.getDbFrom())).get();
            SysDbConnection dbTo=sysDbConnectionRespository.findById(Integer.parseInt(job.getDbTo())).get();
            KettleEnvironment.init();

            TransMeta transMeta = new TransMeta();
            //设置转化的名称
            transMeta.setName(job.getJobName());

            //添加转换的数据库连接
            DatabaseMeta databaseMeta= new DatabaseMeta();
            databaseMeta.setDBName(dbfrom.getDb());
            databaseMeta.setHostname(dbfrom.getHost());
            databaseMeta.setDBPort(dbfrom.getPort());
            databaseMeta.setUsername(dbfrom.getUsername());
            databaseMeta.setPassword(dbfrom.getPassword());
            databaseMeta.setName(job.getTableFrom());
            databaseMeta.setDatabaseType(dbfrom.getType());
            transMeta.addDatabase(databaseMeta);
            DatabaseMeta databaseMeta1= new DatabaseMeta();
            databaseMeta1.setDBName(dbTo.getDb());
            databaseMeta1.setHostname(dbTo.getHost());
            databaseMeta1.setDBPort(dbTo.getPort());
            databaseMeta1.setUsername(dbTo.getUsername());
            databaseMeta1.setPassword(dbTo.getPassword());
            databaseMeta1.setName(job.getTableTo());
            databaseMeta1.setDatabaseType(dbTo.getType());
            transMeta.addDatabase(databaseMeta1);
            //日志数据库
            DatabaseMeta logMeta1= new DatabaseMeta();
            logMeta1.setDBName(dbTo.getDb());
            logMeta1.setHostname(dbTo.getHost());
            logMeta1.setDBPort(dbTo.getPort());
            logMeta1.setUsername(dbTo.getUsername());
            logMeta1.setPassword(dbTo.getPassword());
            logMeta1.setName("log");
            logMeta1.setDatabaseType(dbTo.getType());
            transMeta.addDatabase(logMeta1);
            VariableSpace space = new Variables();
            //将step日志数据库配置名加入到变量集中
            space.setVariable("kettle_log", "log");
            space.initializeVariablesFrom(null);
            StepLogTable stepLogTable = StepLogTable.getDefault(space, transMeta);
            //StepLogTable使用的数据库连接名（上面配置的变量名）。
            stepLogTable.setConnectionName("log");
            //设置Step日志的表名
            stepLogTable.setTableName(kettle_log);
            //设置TransMeta的StepLogTable
            transMeta.setStepLogTable(stepLogTable);

            //registry是给每个步骤生成一个标识Id用
            PluginRegistry registry = PluginRegistry.getInstance();

            //******************************************************************

            //第一个表输入步骤(TableInputMeta)
            TableInputMeta tableInput = new TableInputMeta();
            String tableInputPluginId = registry.getPluginId(StepPluginType.class, tableInput);
            //给表输入添加一个DatabaseMeta连接数据库
            DatabaseMeta database_bjdt = transMeta.findDatabase(job.getTableFrom());
            tableInput.setDatabaseMeta(database_bjdt);
            if(StringUtils.isEmpty(job.getSelectClause())){
                String select_sql = "SELECT ID,IP,CREATEDATETIME,LOGINNAME,TYPE FROM " + job.getTableFrom();
                tableInput.setSQL(select_sql);
            }else{
                String select_sql = "SELECT ID,IP,CREATEDATETIME,LOGINNAME,TYPE FROM " + job.getTableFrom()+ " Where "+job.getSelectClause();
                tableInput.setSQL(select_sql);
            }



            //添加TableInputMeta到转换中
            StepMeta tableInputMetaStep = new StepMeta("INPUTTABLE_" + job.getTableFrom(), tableInput);
            //给步骤添加在spoon工具中的显示位置
            tableInputMetaStep.setDraw(true);
            tableInputMetaStep.setLocation(100, 100);
            transMeta.addStep(tableInputMetaStep);
            //******************************************************************

            //******************************************************************
            //第二个步骤插入与更新
            InsertUpdateMeta insertUpdateMeta = new InsertUpdateMeta();
            insertUpdateMeta.setCommitSize(10000); //性能设置
            String insertUpdateMetaPluginId = registry.getPluginId(StepPluginType.class, insertUpdateMeta);
            //添加数据库连接
            DatabaseMeta database_kettle = transMeta.findDatabase(job.getTableTo());
            insertUpdateMeta.setDatabaseMeta(database_kettle);
            //设置操作的表
            insertUpdateMeta.setTableName(job.getTableTo());

            //设置用来查询的关键字
            insertUpdateMeta.setKeyLookup(new String[]{"ID"});
            insertUpdateMeta.setKeyStream(new String[]{"ID"});
            insertUpdateMeta.setKeyStream2(new String[]{""});//一定要加上
            insertUpdateMeta.setKeyCondition(new String[]{"="});

            //String select_sql = "SELECT ID,IP,CREATEDATETIME,LOGINNAME,TYPE FROM "+bjdt_tablename;
            //设置要更新的字段
            Arrays.stream(job.getUpdateClause().split(",")).forEach(e->System.out.print(e));
           // System.out.print();
            String[] updatelookup ={"ID", "IP", "CREATEDATETIME", "LOGINNAME", "TYPE"};
            String[] updateStream = {"ID", "IP", "CREATEDATETIME", "LOGINNAME", "TYPE"};
           Boolean[] updateOrNot = {false, true, true, true, true};
            insertUpdateMeta.setUpdateLookup(updatelookup);
            insertUpdateMeta.setUpdateStream(updateStream);
            insertUpdateMeta.setUpdate(updateOrNot);
            String[] lookup = insertUpdateMeta.getUpdateLookup();
            //添加步骤到转换中
            StepMeta insertUpdateStep = new StepMeta("INSERTUPDATE_" + job.getTableTo(), insertUpdateMeta);
            insertUpdateStep.setDraw(true);
            insertUpdateStep.setLocation(250, 100);
            transMeta.addStep(insertUpdateStep);
            //******************************************************************


            //******************************************************************
            //添加hop把两个步骤关联起来
            transMeta.addTransHop(new TransHopMeta(tableInputMetaStep, insertUpdateStep));


            Trans trans = new Trans(transMeta);

            trans.execute(null); // You can pass arguments instead of null.
            trans.waitUntilFinished();
            if (trans.getErrors() > 0) {
                throw new RuntimeException("There were errors during transformation execution.");
            }
            System.out.println("***********the end************");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }
    public static void main(String[] args){
        String str="ID, IP, CREATEDATETIME, LOGINNAME, TYPE";
        String[] arra=str.split(",");
        System.out.print(arra);
        String[] arra1= {"ID", "IP", "CREATEDATETIME", "LOGINNAME", "TYPE"};
        System.out.print(arra1);
    }

}
