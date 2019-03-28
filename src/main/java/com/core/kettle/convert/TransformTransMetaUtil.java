package com.core.kettle.convert;

import com.core.kettle.bean.SysConfigJob;
import com.core.kettle.service.KettleService;
import com.core.kettle.util.KettleUtil;
import javassist.bytecode.stackmap.BasicBlock;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.steps.calculator.CalculatorMeta;
import org.pentaho.di.trans.steps.calculator.CalculatorMetaFunction;
import org.pentaho.di.trans.steps.constant.ConstantMeta;
import org.pentaho.di.trans.steps.databaselookup.DatabaseLookupMeta;
import org.pentaho.di.trans.steps.stepmeta.StepMetastructureMeta;
import org.pentaho.di.trans.steps.tableinput.TableInputMeta;
import org.pentaho.di.trans.steps.textfileoutput.TextFileOutputMeta;

import java.util.Arrays;

/**
 *  此类专门处理字段的转换
 */
public class TransformTransMetaUtil<main> {
    public static final String module="Transform_";
    public static StepMeta getConst(TransMeta transMeta,StepMeta prevStepMeta,String fieldStr){
        ConstantMeta c=new ConstantMeta();
        c.setDefault();
        c.setFieldName(new String[]{"grey_type"});
        c.setValue(new String[]{"paohaijiao1234"});
        c.setFieldType(new  String[]{"String"});
        StepMeta dummyStep = new StepMeta("const",c);
        transMeta.addStep(dummyStep);
        TransHopMeta hop = new TransHopMeta(prevStepMeta, dummyStep);
        transMeta.addTransHop(hop);
        return dummyStep;
    }

    /**
     *    获取数据库字段
     * @param transMeta
     * @param databaseMeta
     * @param table
     * @param schema
     * @return
     */
    public static  String[] getTableMeta(TransMeta transMeta,DatabaseMeta databaseMeta,String table ,String schema){
        TableInputMeta tableInput=new TableInputMeta();
        tableInput.setDefault();
        tableInput.setDatabaseMeta(databaseMeta);
        tableInput.setSQL("SELECT * FROM financial.itf_grey_list");
        StepMeta tableInputMetaStep = new StepMeta("DB_META_", tableInput);
        transMeta.addStep(tableInputMetaStep);
        StepMetastructureMeta stepMetastructureMeta=new StepMetastructureMeta();
        stepMetastructureMeta.setDefault();
        StepMeta stepMetastructureMetaStep = new StepMeta("Struct_", stepMetastructureMeta);
        transMeta.addStep(stepMetastructureMetaStep);
        TransHopMeta structhop = new TransHopMeta(tableInputMetaStep, stepMetastructureMetaStep);
        transMeta.addTransHop(structhop);

        TextFileOutputMeta outMeta=new TextFileOutputMeta();
        outMeta.setDefault();
        outMeta.setHeaderEnabled(false);
      //  outMeta.setOutputFields(KettleUtil.getFixedField());
        outMeta.setFileName("C:\\Users\\acer\\Desktop\\data\\sys_db_connection1.csv");
        StepMeta dummyStep = new StepMeta("out",outMeta);
        transMeta.addStep(dummyStep);

        TransHopMeta hop = new TransHopMeta(stepMetastructureMetaStep, dummyStep);
        transMeta.addTransHop(hop);
        return new String[]{"id"};
    }

    public static void main(String[] args) {
        try{
            KettleEnvironment.init();

            TransMeta transMeta = new TransMeta();
            transMeta.setName("获取数据库表字段");
            DatabaseMeta databaseMeta= new DatabaseMeta();
            databaseMeta.setDBName("sync1");
            databaseMeta.setHostname("192.168.0.250");
            databaseMeta.setDBPort("5432");
            databaseMeta.setUsername("postgres");
            databaseMeta.setPassword("123456-250");
            databaseMeta.setName("dev_db_sync1");
            databaseMeta.setDatabaseType("PostgreSQL");
           getTableMeta(transMeta,databaseMeta,"financial","itf_grey_list");

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
            System.out.println(e);
        }

    }



}