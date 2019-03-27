package com.core.kettle.util;

import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.row.ValueMetaInterface;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.steps.textfileoutput.TextFileField;

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
   public static TextFileField[] getFixedField(){
       TextFileField f1=new TextFileField();
       f1.setTrimType(ValueMetaInterface.TRIM_TYPE_BOTH);
       f1.setFormat("#");
       f1.setName("id");
       f1.setType(ValueMetaInterface.TYPE_STRING);
       TextFileField f2=new TextFileField();
       f2.setTrimType(ValueMetaInterface.TRIM_TYPE_BOTH);
       f2.setName("ent_name");
       f2.setFormat("#");
       f2.setNullString("默认值");
       f2.setType(ValueMetaInterface.TYPE_STRING);
       TextFileField f3=new TextFileField();
       f3.setTrimType(ValueMetaInterface.TRIM_TYPE_BOTH);
       f3.setFormat("#");
       f3.setName("grey_type");
       f3.setType(ValueMetaInterface.TYPE_STRING);
       TextFileField f4=new TextFileField();
       f4.setTrimType(ValueMetaInterface.TRIM_TYPE_BOTH);
       f4.setFormat("#");
       f4.setName("describe");
       f4.setType(ValueMetaInterface.TYPE_STRING);
       TextFileField f5=new TextFileField();
       f5.setTrimType(ValueMetaInterface.TRIM_TYPE_BOTH);
       f5.setFormat("#");
       f5.setName("create_by");
       f5.setType(ValueMetaInterface.TYPE_STRING);
       TextFileField f6=new TextFileField();
       f6.setTrimType(ValueMetaInterface.TRIM_TYPE_BOTH);
       f6.setFormat("yyyy/MM/dd HH:mm:ss");
       f6.setType(ValueMetaInterface.TYPE_DATE);
       f6.setName("date_created");
       TextFileField f7=new TextFileField();
       f7.setTrimType(ValueMetaInterface.TRIM_TYPE_BOTH);
       f7.setFormat("#");
       f7.setType(ValueMetaInterface.TYPE_STRING);
       f7.setName("grey_flag");
       return new TextFileField[]{f1,f2,f3,f4,f5,f6,f7};
   }
}