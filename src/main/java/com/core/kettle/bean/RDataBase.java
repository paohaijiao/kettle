package com.core.kettle.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "R_DATABASE")
public class RDataBase {
    @Id
    @Column(name = "idDatabase")
    private int idDatabase;
    @Column(name = "name")
    private String name;
    @Column(name = "idDatabaseContype")
    private String idDatabaseContype;
    @Column(name = "hostName")
    private String hostName;
    @Column(name = "databaseName")
    private String databaseName;
    @Column(name = "userName")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "serverName")
    private String serverName;
    @Column(name = "dataTbs")
    private String dataTbs;
    @Column(name = "indexTbs")
    private String indexTbs;
}
