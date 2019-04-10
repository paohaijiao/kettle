package com.core.kettle.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "R_DATABASE_TYPE")
public class RDatabaseType {
    @Id
    @Column(name = "idDatabseType")
    private int idDatabseType;
    @Column(name = "code")
    private String code;
    @Column(name = "description")
    private String description;
}
