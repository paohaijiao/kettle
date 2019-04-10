package com.core.kettle.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "R_STEP_TYPE")
public class RStepType {
    @Id
    @Column(name = "idStepType")
    private int idStepType;
    @Column(name = "code")
    private String code;
    @Column(name = "description")
    private String description;
    @Column(name = "helptext")
    private String helptext;
}
