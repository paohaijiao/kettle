package com.core.kettle.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "R_TRANS_HOP")
public class RTransHop {
    @Id
    @Column(name = "idTransHop")
    private int idTransHop;
    @Column(name = "idTransformation")
    private int idTransformation;
    @Column(name = "idStepFrom")
    private int idStepFrom;
    @Column(name = "idStepTo")
    private int idStepTo;
    @Column(name = "enabled")
    private int enabled;
}
