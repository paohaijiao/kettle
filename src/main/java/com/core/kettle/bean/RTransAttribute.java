package com.core.kettle.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "R_TRANS_ATTRIBUTE")
public class RTransAttribute {
    @Id
    @Column(name = "idTransAttribute")
    private int idTransAttribute;
    @Column(name = "idTransformation")
    private int idTransformation;
    @Column(name = "nr")
    private int nr;
    @Column(name = "code")
    private String code;
    @Column(name = "valueNum")
    private int valueNum;
    @Column(name = "valueStr")
    private String valueStr;

}
