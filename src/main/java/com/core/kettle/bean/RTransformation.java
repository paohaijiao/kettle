package com.core.kettle.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "R_TRANSFORMATION")
public class RTransformation {
    @Id
    @Column(name = "idTransformation")
    private int idTransformation;
    @Column(name = "idDirectory")
    private int idDirectory;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "extendedDescription")
    private String extendedDescription;
    @Column(name = "transVersion")
    private String transVersion;
    @Column(name = "transStatus")
    private String transStatus;
    @Column(name = "idStepRead")
    private int idStepRead;
    @Column(name = "idStepWrite")
    private int idStepWrite;
    @Column(name = "idStepInput")
    private int idStepInput;
    @Column(name = "idStepOutput")
    private int idStepOutput;
    @Column(name = "idStepUpdate")
    private int idStepUpdate;
    @Column(name = "idDatabaseLog")
    private int idDatabaseLog;
    @Column(name = "tableNamelog")
    private String tableNamelog;
    @Column(name = "useBatchid")
    private int useBatchid;
    @Column(name = "useLogfield")
    private int useLogfield;
    @Column(name = "idDatabaseMaxdate")
    private int idDatabaseMaxdate;
    @Column(name = "tableNameMaxdate")
    private String tableNameMaxdate;
    @Column(name = "fieldNameMaxdate")
    private String fieldNameMaxdate;
    @Column(name = "diffMaxdate")
    private String diffMaxdate;
    @Column(name = "offsetMaxdate")
    private double offsetMaxdate;
    @Column(name = "createdUser")
    private String createdUser;
    @Column(name = "createdDate")
    private Date createdDate;
    @Column(name = "modifiedUser")
    private String modifiedUser;
    @Column(name = "modifiedDate")
    private Date modifiedDate;
    @Column(name = "sizeRowset")
    private int sizeRowset;

}
