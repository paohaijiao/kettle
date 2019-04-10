package com.core.kettle.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "R_DATABASE_ATTRIBUTE")
public class RDatabaseAttribute {
    @Id
    @Column(name = "idDatabaseAttribute")
    private int idDatabaseAttribute;
    @Column(name = "idDatabse")
    private int idDatabse;
    @Column(name = "code")
    private String code;
    @Column(name = "valueStr")
    private String valueStr;
}
