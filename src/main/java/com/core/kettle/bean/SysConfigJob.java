package com.core.kettle.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="sys_config_job")
public class SysConfigJob {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="tableFrom")
    private String tableFrom;
    @Column(name="tableTo")
    private String tableTo;
    @Column(name="dbTo")
    private String dbTo;
    @Column(name="dbFrom")
    private String dbFrom;
    @Column(name="status")
    private String status;
    @Column(name="jobName")
    private String jobName;
    @Column(name="create_by")
    private String createBy;
    @Column(name="date_create")
    private Date dateCreated;
    @Column(name="updateBy")
    private String upadteBy;
    @Column(name="dateUpdate")
    private  Date dateUpdate;
    @Column(name="updateClause")
    private  String updateClause;
    @Column(name="selectClause")
    private  String selectClause;
    @Column(name="shemaFrom")
    private  String shemaFrom;
    @Column(name="shemaTo")
    private  String shemaTo;

    public String getShemaFrom() {
        return shemaFrom;
    }

    public void setShemaFrom(String shemaFrom) {
        this.shemaFrom = shemaFrom;
    }

    public String getShemaTo() {
        return shemaTo;
    }

    public void setShemaTo(String shemaTo) {
        this.shemaTo = shemaTo;
    }

    public String getUpdateClause() {
        return updateClause;
    }

    public void setUpdateClause(String updateClause) {
        this.updateClause = updateClause;
    }

    public String getSelectClause() {
        return selectClause;
    }

    public void setSelectClause(String selectClause) {
        this.selectClause = selectClause;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTableFrom() {
        return tableFrom;
    }

    public void setTableFrom(String tableFrom) {
        this.tableFrom = tableFrom;
    }

    public String getTableTo() {
        return tableTo;
    }

    public void setTableTo(String tableTo) {
        this.tableTo = tableTo;
    }

    public String getDbTo() {
        return dbTo;
    }

    public void setDbTo(String dbTo) {
        this.dbTo = dbTo;
    }

    public String getDbFrom() {
        return dbFrom;
    }

    public void setDbFrom(String dbFrom) {
        this.dbFrom = dbFrom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getUpadteBy() {
        return upadteBy;
    }

    public void setUpadteBy(String upadteBy) {
        this.upadteBy = upadteBy;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}
