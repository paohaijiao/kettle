package com.core.kettle.convert;

import com.core.kettle.util.KettleUtil;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.steps.ifnull.IfNullMeta;
import org.pentaho.di.trans.steps.stepmeta.StepMetastructureMeta;
import org.pentaho.di.trans.steps.tableinput.TableInputMeta;
import org.pentaho.di.trans.steps.tableoutput.TableOutputMeta;
import org.pentaho.di.trans.steps.textfileoutput.TextFileOutputMeta;


/**
 *  此类专门处理字段的转换
 */
public class TransformTransMetaUtil<main> {
    public static final String module="Transform_";

    /**
     * 对空字段去除null值
     *
     * @param transMeta
     * @param prevStepMeta
     * @param str
     * @return
     */
    public static StepMeta trimNull(TransMeta transMeta, StepMeta prevStepMeta, String str) {
        IfNullMeta ifNullMeta = new IfNullMeta();
        ifNullMeta.setDefault();
        ifNullMeta.setReplaceAllByValue(str);
        StepMeta ifNullMetaStep = new StepMeta("ifNullMeta", ifNullMeta);
        transMeta.addStep(ifNullMetaStep);
        TransHopMeta hop = new TransHopMeta(prevStepMeta, ifNullMetaStep);
        transMeta.addTransHop(hop);
        return ifNullMetaStep;
    }

    /**
     * 输出文件到指定路径
     *
     * @param transMeta
     * @param prevStepMeta
     * @param fileName
     * @return
     */
    public static StepMeta outPutTextFile(TransMeta transMeta, StepMeta prevStepMeta, String fileName, String fieldFormat) throws Exception {
        TextFileOutputMeta textFileOutputMeta = new TextFileOutputMeta();
        textFileOutputMeta.setDefault();
        textFileOutputMeta.setHeaderEnabled(false);
        textFileOutputMeta.setFileName(fileName);
        textFileOutputMeta.setEncoding("utf-8");
        textFileOutputMeta.setOutputFields(KettleUtil.getFixedField(fieldFormat));
        StepMeta textoutPutMeta = new StepMeta("TextoutPutMeta", textFileOutputMeta);
        transMeta.addStep(textoutPutMeta);
        TransHopMeta hop = new TransHopMeta(prevStepMeta, textoutPutMeta);
        transMeta.addTransHop(hop);
        return textoutPutMeta;
    }
    /**    获取数据库字段
     *
     * @param transMeta
     * @param databaseMeta
     * @param table
     * @param schema
     */
    public static void getTableMetaInfo(TransMeta transMeta, DatabaseMeta databaseMeta, String table, String schema){
        TableInputMeta tableInput=new TableInputMeta();
        tableInput.setDefault();
        tableInput.setDatabaseMeta(databaseMeta);
        tableInput.setSQL("SELECT * FROM " + schema + "." + table);
        StepMeta tableInputMetaStep = new StepMeta("DB_META_", tableInput);
        transMeta.addStep(tableInputMetaStep);
        StepMetastructureMeta stepMetastructureMeta=new StepMetastructureMeta();
        stepMetastructureMeta.setDefault();
        StepMeta stepMetastructureMetaStep = new StepMeta("Struct_", stepMetastructureMeta);
        transMeta.addStep(stepMetastructureMetaStep);
        TransHopMeta structhop = new TransHopMeta(tableInputMetaStep, stepMetastructureMetaStep);//第一个转换把数据库输入转换成原数据
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
        TransHopMeta hop = new TransHopMeta(stepMetastructureMetaStep, outputStep);//把元数据存入表里面
        transMeta.addTransHop(hop);
    }


    public static void main(String[] args) {
        try{
            KettleEnvironment.init();
            TransMeta transMeta = new TransMeta();
            transMeta.setName("去除null值");
            DatabaseMeta databaseMeta= new DatabaseMeta();
            databaseMeta.setDBName("sync1");
            databaseMeta.setHostname("192.168.0.250");
            databaseMeta.setDBPort("5432");
            databaseMeta.setUsername("postgres");
            databaseMeta.setPassword("123456-250");
            databaseMeta.setName("dev_db_sync1");
            databaseMeta.setDatabaseType("PostgreSQL");
            TableInputMeta tableInput = new TableInputMeta();
            tableInput.setDefault();
            tableInput.setDatabaseMeta(databaseMeta);
            tableInput.setSQL("SELECT * FROM financial.itf_grey_list");
            StepMeta tableInputMetaStep = new StepMeta("DB_META_", tableInput);
            transMeta.addStep(tableInputMetaStep);
            StepMeta inputStep = trimNull(transMeta, tableInputMetaStep, "新值替换");
            String format = "id,#,int;ent_name,#,string;grey_type,#,string;describe,#,string;create_by,#,string;date_created,yyyy/MM/dd  HH:mm:ss,timestamp;grey_flag,#,timestamp;";
            StepMeta step = outPutTextFile(transMeta, inputStep, "C:\\Users\\acer\\Desktop\\data\\sys_db_connection1", format);
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