package com.core.kettle.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "R_STEP_ATTRIBUTE")
public class RStepAttribute {
    @Id
    @Column(name = "idStepAttribute")
    private int idStepAttribute;
    @Column(name = "idTransformation")
    private int idTransformation;
    @Column(name = "idStep")
    private int idStep;
    @Column(name = "nr")
    private int nr;
    @Column(name = "code")
    private String code;
    @Column(name = "valueNum")
    private int valueNum;
    @Column(name = "valueStr")
    private String valueStr;
}
