package com.core.kettle.convert;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.steps.constant.ConstantMeta;
import org.pentaho.di.trans.steps.stepmeta.StepMetastructureMeta;
import org.pentaho.di.trans.steps.tableinput.TableInputMeta;
import org.pentaho.di.trans.steps.tableoutput.TableOutputMeta;
import org.pentaho.di.trans.steps.textfileoutput.TextFileOutputMeta;


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
    /**    获取数据库字段
     *
     * @param transMeta
     * @param databaseMeta
     * @param table
     * @param schema
     */
    public static  void getTableMeta(TransMeta transMeta,DatabaseMeta databaseMeta,String table ,String schema){
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
        TableOutputMeta outputMeta=new TableOutputMeta();
        outputMeta.setDefault();
        outputMeta.setSpecifyFields(true);
        outputMeta.setDatabaseMeta(databaseMeta);
        outputMeta.setFieldStream(new String[]{"位置","字段名","注释","类型","长度","精度","原"});
        outputMeta.setFieldDatabase(new   String[]{"index","column_name","column_comment","column_type","length","precise","table_name"});
        outputMeta.setTableName(table);
        outputMeta.setSchemaName(schema);
        StepMeta outputStep = new StepMeta("out",outputMeta);
        transMeta.addStep(outputStep);

        TransHopMeta hop = new TransHopMeta(stepMetastructureMetaStep, outputStep);
        transMeta.addTransHop(hop);
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
           getTableMeta(transMeta,databaseMeta,"sys_meta_mapping","financial");

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