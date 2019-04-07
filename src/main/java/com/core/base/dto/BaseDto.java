package com.core.base.dto;

import javax.persistence.Column;
import java.util.Date;

public class BaseDto {

    @Column(name = "createDate")
    private Date createDate;
    @Column(name = "createBy")
    private String createBy;
    @Column(name = "updateDate")
    private Date updateDate;
    @Column(name = "updateBy")
    private String updateBy;
    @Column(name = "effectveFlag")
    private String effectveFlag;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getEffectveFlag() {
        return effectveFlag;
    }

    public void setEffectveFlag(String effectveFlag) {
        this.effectveFlag = effectveFlag;
    }
}
