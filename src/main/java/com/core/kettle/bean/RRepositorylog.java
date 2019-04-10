package com.core.kettle.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "R_REPOSITORY_LOG")
public class RRepositorylog {
    @Id
    @Column(name = "idRepositoryLog")
    private int idRepositoryLog;
    @Column(name = "repVersion")
    private String repVersion;
    @Column(name = "logDate")
    private Date logDate;
    @Column(name = "logUser")
    private String logUser;
    @Column(name = "operationDesc")
    private String operationDesc;
}
