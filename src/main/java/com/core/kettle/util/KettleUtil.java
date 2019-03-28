package com.core.kettle.util;

import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.row.ValueMetaInterface;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.steps.textfileoutput.TextFileField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KettleUtil {
   public static void addDatabase(TransMeta transMeta){
       DatabaseMeta databaseMeta= new DatabaseMeta();
       databaseMeta.setDBName("sync1");
       databaseMeta.setHostname("192.168.0.250");
       databaseMeta.setDBPort("5432");
       databaseMeta.setUsername("postgres");
       databaseMeta.setPassword("123456-250");
       databaseMeta.setName("dev_db_sync1");
       databaseMeta.setDatabaseType("PostgreSQL");
       transMeta.addDatabase(databaseMeta);
   }

    public static TextFileField[] getFixedField(String fieldFormat) throws Exception {
        TextFileField[] strings = new TextFileField[fieldFormat.split(";").length];
        List<TextFileField> list = new ArrayList<TextFileField>();
        if (null == fieldFormat) {
            throw new Exception("fieldFormat不能为空");
        } else {
            String[] fields = fieldFormat.split(";");
            Arrays.stream(fields).forEach(e -> {
                String[] field = e.split(",");
                TextFileField f1 = new TextFileField();
                f1.setName(field[0]);
                f1.setTrimType(ValueMetaInterface.TRIM_TYPE_BOTH);
                f1.setFormat(field[1]);
                f1.setType(getInstance(field[2]));
                list.add(f1);
            });
        }
        return list.toArray(strings);
   }

    public static int getInstance(String type) {
        int code = 0;
        switch (type) {
            case "number":
                code = ValueMetaInterface.TYPE_NUMBER;
                break;
            case "string":
                code = ValueMetaInterface.TYPE_STRING;
                break;
            case "date":
                code = ValueMetaInterface.TYPE_DATE;
                break;
            case "boolean":
                code = ValueMetaInterface.TYPE_BOOLEAN;
                break;
            case "integer":
                code = ValueMetaInterface.TYPE_INTEGER;
                break;
            case "bignumber":
                code = ValueMetaInterface.TYPE_BIGNUMBER;
                break;
            case "serializable":
                code = ValueMetaInterface.TYPE_SERIALIZABLE;
                break;
            case "binary":
                code = ValueMetaInterface.TYPE_BINARY;
                break;
            case "timestamp":
                code = ValueMetaInterface.TYPE_TIMESTAMP;
                break;
            case "inet":
                code = ValueMetaInterface.TYPE_INET;
                break;
            default:
                code = ValueMetaInterface.TYPE_NONE;
        }
        return code;

    }

}