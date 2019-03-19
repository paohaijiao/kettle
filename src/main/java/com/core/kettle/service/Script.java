package com.core.kettle.service;

import java.sql.*;

public class Script {
    static String insert = "INSERT INTO `sys_config_job`( `schema_from`,`table_from`, `table_to`, `db_to`, `db_from`, `status`, `job_name`, `update_clause`, `select_clause`, `create_by`, `date_create`, `update_by`, `date_update`,`schema_to`) VALUES ( 'financial','%s', '%s', '8', '9', 'Y', 'JOB', '%s', '%s', 'SYS', '2019-03-11', 'SYS', '2019-03-11','financial');";
    static String url = "jdbc:postgresql://192.168.0.250:5432/ods?currentSchema=financial";
    static String usr = "postgres";
    static String psd = "123456-250";
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, usr, psd);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select tablename from pg_tables where schemaname='financial'");
            while (rs.next()) {
                String tableName=rs.getString(1);
                Connection   connection1 = DriverManager.getConnection(url, usr, psd);
                Statement st1 = connection1.createStatement();
                String sql="select string_agg(column_name,',') as string_agg from information_schema.columns where table_name='"+tableName+"'";
                ResultSet rs1 = st1.executeQuery(sql);
                while (rs1.next()) {
                    String column=rs1.getString("string_agg");
                    if(null==column){
                        return;
                    }
                    String name = String.format(insert, tableName, tableName, column, column);
                    System.out.println(name);
                }
            }


        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    }
}

