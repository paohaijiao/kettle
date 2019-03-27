package com.core.kettle.service;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.RowMetaAndData;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleStepException;
import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.core.row.ValueMetaInterface;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.RowListener;
import org.pentaho.di.trans.step.StepInterface;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.steps.csvinput.CsvInputMeta;
import org.pentaho.di.trans.steps.dummytrans.DummyTransMeta;
import org.pentaho.di.trans.steps.textfileinput.TextFileInputField;
import org.pentaho.di.trans.steps.textfileoutput.TextFileOutputMeta;

import java.util.ArrayList;
import java.util.List;

public class CsvFileReader {
    private static String STEP_READ_A_FILE = "Read a file";
    private static String STEP_DUMMY = "Dum12my";

    private String fileName;
    private TextFileInputField[] inputFields;
    private List<RowMetaAndData> rows;

    public CsvFileReader(String filename,TextFileInputField[] inputFields) {
        this.fileName = filename;
        this.inputFields = inputFields;
    }

    public void read() throws KettleException {
        KettleEnvironment.init();
        //transformation
        TransMeta transMeta = new TransMeta();
        transMeta.setName("sample04");//定义转换名称

        CsvInputMeta inputMeta = new CsvInputMeta();
        inputMeta.setDefault();
        inputMeta.setFilename(fileName);
        inputMeta.setInputFields(inputFields);

        StepMeta inputStep = new StepMeta(STEP_READ_A_FILE,inputMeta);
        transMeta.addStep(inputStep);

        TextFileOutputMeta outMeta=new TextFileOutputMeta();
        outMeta.setDefault();
        outMeta.setFileName("C:\\Users\\acer\\Desktop\\sys_db_connection1.csv");
        StepMeta dummyStep = new StepMeta(STEP_DUMMY,outMeta);
        transMeta.addStep(dummyStep);
        TransHopMeta hop = new TransHopMeta(inputStep, dummyStep);
        transMeta.addTransHop(hop);
        Trans trans = new Trans(transMeta);
        trans.prepareExecution(null);

        rows = new ArrayList<RowMetaAndData>();
        RowListener rowLinstener = new RowListener() {
            @Override
            public void rowWrittenEvent(RowMetaInterface rowMeta, Object[] row) throws KettleStepException {
                rows.add(new RowMetaAndData(rowMeta, row));
            }
            @Override
            public void rowReadEvent(RowMetaInterface arg0, Object[] arg1) throws KettleStepException {
            }
            @Override
            public void errorRowWrittenEvent(RowMetaInterface arg0, Object[] arg1) throws KettleStepException {
            }
        };
        StepInterface setpInterface = trans.findRunThread(STEP_DUMMY);
        setpInterface.addRowListener(rowLinstener);

        trans.startThreads();
        trans.waitUntilFinished();

        if(trans.getErrors() > 0) {
            System.out.println(">>>>>>>>>> ERROR");
        }else {
          //  System.out.println(">>>>>>>>>> Row size "+rows.size());
        }

    }

    public static void main(String[] args) {
        String file = "C:\\Users\\acer\\Desktop\\sys_db_connection.csv";

        TextFileInputField no1 = new TextFileInputField("id", -1, 8);
        no1.setTrimType(ValueMetaInterface.TRIM_TYPE_BOTH);
        no1.setFormat("#");
        no1.setType(ValueMetaInterface.TYPE_INTEGER);

        TextFileInputField no2 = new TextFileInputField("ent_name", -1, 50);
        no2.setTrimType(ValueMetaInterface.TRIM_TYPE_BOTH);
        no2.setFormat("#");
        no2.setType(ValueMetaInterface.TYPE_STRING);

        TextFileInputField[] inputs = new TextFileInputField[] {no1,no2};

        CsvFileReader reader = new CsvFileReader(file, inputs);
        try {
            reader.read();
        } catch (KettleException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}