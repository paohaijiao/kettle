package com.core.kettle;

import com.core.kettle.convert.TransformTransMetaUtil;
import com.core.kettle.util.KettleUtil;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.core.row.ValueMetaInterface;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.steps.tableinput.TableInputMeta;
import org.pentaho.di.trans.steps.textfileoutput.TextFileOutputMeta;

public class Main {
    public static void main(String[] args) {
        try{
            KettleEnvironment.init();
            TransMeta transMeta = new TransMeta();
            transMeta.setName("Test");
            KettleUtil.addDatabase(transMeta);
            TableInputMeta tableInput = new TableInputMeta();
           DatabaseMeta database = transMeta.findDatabase("dev_db_sync1");
            tableInput.setDatabaseMeta(database);
            tableInput.setSQL("SELECT id,ent_name,grey_type,describe,create_by,date_created,grey_flag FROM  financial.itf_grey_list");
           // StepMeta previous=TransformTransMetaUtil.trimNullBasedOnFields(transMeta,tableInputMetaStep,"");
            //c.setTablenameFieldName();
            StepMeta tableInputMetaStep = new StepMeta("INPUTTABLE_itf_grey_list", tableInput);
            transMeta.addStep(tableInputMetaStep);
            TextFileOutputMeta outMeta=new TextFileOutputMeta();
            outMeta.setDefault();
            //outMeta.setFileNameField("id,ent_name,grey_type,describe,create_by,date_created,grey_flag");
            outMeta.setHeaderEnabled(false);
            outMeta.setOutputFields(KettleUtil.getFixedField());
            outMeta.setFileName("C:\\Users\\acer\\Desktop\\data\\sys_db_connection1.csv");
            StepMeta dummyStep = new StepMeta("out",outMeta);
            transMeta.addStep(dummyStep);
            TransHopMeta hop = new TransHopMeta(tableInputMetaStep, dummyStep);
            transMeta.addTransHop(hop);
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