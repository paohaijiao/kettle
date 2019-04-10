package com.core.kettle.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "R_STEP_DATABASE")
public class RStepDatabase {
    @Id
    @Column(name = "idTransformation")
    private int idTransformation;
    @Column(name = "idStep")
    private int idStep;
    @Column(name = "idDatabase")
    private int idDatabase;
}
